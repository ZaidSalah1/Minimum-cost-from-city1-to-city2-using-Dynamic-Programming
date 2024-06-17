package algorthimproject1;

import java.util.ArrayList;
import java.util.List;

public class MinCostPath {

    static final int INF = Integer.MAX_VALUE;
    public static StringBuilder DPTable = new StringBuilder();
    private static boolean readed = false;

    // Function to find the minimum cost traversal from start city to end city
    static PathWithCost minimumCostTraversal(City[] cities, String startCity, String endCity) {
        int n = cities.length;
        int[][] dp = new int[n][n];
        int[][] next = new int[n][n]; // To store the next city in the path

        // Initialize dp array with maximum values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Base case: cost of traveling from a city to itself is 0
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // Initialize the adjacency matrix
        for (City city : cities) {
            int cityIndex = getIndex(cities, city.name);
            for (Neighbor neighbor : city.neighbors) {
                int neighborIndex = getIndex(cities, neighbor.city);
                dp[cityIndex][neighborIndex] = neighbor.petrolCost + neighbor.hotelCost;
                next[cityIndex][neighborIndex] = neighborIndex; // Set the next city
            }
        }

        // Dynamic Programming: Find minimum cost path from startCity to endCity
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
                        if (dp[i][j] > dp[i][k] + dp[k][j]) {
                            dp[i][j] = dp[i][k] + dp[k][j];
                            next[i][j] = next[i][k]; // Update the next city in the path
                        }
                    }
                }
            }
        }

        if (readed == false) {
            readed = true;
            // Print Dynamic Programming Table with city names
            DPTable.append("Dynamic Programming Table:\n");
            DPTable.append("     ");
            for (City city : cities) {
                DPTable.append(city.name).append("\t");
            }
            System.out.println();
            DPTable.append("\n");
            for (int i = 0; i < n; i++) {
                DPTable.append(cities[i].name).append(" | ");
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] == Integer.MAX_VALUE) {
                        DPTable.append("INF\t");
                    } else {
                        DPTable.append(dp[i][j]).append("\t");
                    }
                }
                DPTable.append("\n");
            }
            DPTable.append("--------------------------------------------------------------------\n\n");
            DPTable.append("Path  Table:\n");
            DPTable.append("     ");
            for (City city : cities) {
                DPTable.append(city.name).append("\t");
            }
            System.out.println();
            DPTable.append("\n");
            for (int i = 0; i < n; i++) {
                DPTable.append(cities[i].name).append(" | ");
                for (int j = 0; j < n; j++) {
                    if (next[i][j] == Integer.MAX_VALUE) {
                        DPTable.append("INF\t");
                    } else {
                        DPTable.append(next[i][j]).append("\t");
                    }
                }
                DPTable.append("\n");
            }

        }

        // Reconstruct the path from startCity to endCity
        List<String> path = new ArrayList<>();
        int startCityIndex = getIndex(cities, startCity);
        int endCityIndex = getIndex(cities, endCity);
        int currentCityIndex = startCityIndex;
        while (currentCityIndex != endCityIndex) {
            path.add(cities[currentCityIndex].name);
            currentCityIndex = next[currentCityIndex][endCityIndex];
        }
        path.add(cities[endCityIndex].name);

        // Return the minimum cost and the path
        return new PathWithCost(dp[startCityIndex][endCityIndex], path);
    }

    // Function to get the index of a city in the cities array
    static int getIndex(City[] cities, String cityName) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].name.equals(cityName)) {
                return i;
            }
        }
        return -1; // City not found
    }

}
// Function to find the minimum cost traversal from start city to end city
//    static int minimumCostTraversal(City[] cities, String startCity, String endCity) {
//        int n = cities.length;
//        int[][] dp = new int[n][n];
//
//        // Initialize dp array with INF values
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                dp[i][j] = INF;
//            }
//        }
//
//        // Base case: cost of traveling from a city to itself is 0
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = 0;
//        }
//
//        // Initialize the adjacency matrix
//        for (City city : cities) {
//            int cityIndex = getIndex(cities, city.name);
//            for (Neighbor neighbor : city.neighbors) {
//                int neighborIndex = getIndex(cities, neighbor.city);
//                dp[cityIndex][neighborIndex] = neighbor.petrolCost + neighbor.hotelCost;
//            }
//        }
//
//        // Dynamic Programming: Find minimum cost path from startCity to endCity
//        for (int k = 0; k < n; k++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (dp[i][k] != INF && dp[k][j] != INF) {
//                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
//                    }
//                }
//            }
//        }
//
//        // Return the minimum cost of traveling from startCity to endCity
//        int startCityIndex = getIndex(cities, startCity);
//        int endCityIndex = getIndex(cities, endCity);
//        return dp[startCityIndex][endCityIndex];
//    }

