# Optimal Strategy for Minimum cost from city1 to city1 using Dynamic Programming

## Problem definition:
1. Travelling from home in city1 to a hotel in city2.
2. N stopovers on the way
   a. a number of choices of towns for each stop,
   b. a number of hotels to choose from in each city.
3. Each trip has a different distance resulting in a different cost (petrol).
4. Hotels have different costs.
5. The goal is to select a route to and a hotel in city2 so that the overall cost of the trip is
minimized.

# Sample Graph Image
![graph](https://github.com/zaidsalah123er/Minimum-cost-from-city1-to-city1-using-Dynamic-Programming/assets/89332179/037ae8c4-c44f-4db1-973a-efe6dae646bc)


# Main Window
![min cost main](https://github.com/zaidsalah123er/Minimum-cost-from-city1-to-city1-using-Dynamic-Programming/assets/89332179/123b4cc3-213c-4247-a604-1bb382104820)

# File Foramt
```
1. Read the input data from a file
File format:
Number of cities
Start city, end city
List of adjacent cities
Example:
14
Start, End
Start, [A,22,70], [B,8,80], [C,12,80]
A, [D,8,50], [E,10,70]
B, [D, 25, 50], [E,10,70]
C, [D,13,50], [E,13,70]
D, [F,25,50], [G,30,70], [H,18,70], [I, 27,60]
E, [F,12,50], [G,10,70], [H,8,70], [I, 7,60]
F, [J,26,50], [K,13,70], [L,15,60]
G, [J,8,50], [K,10,70], [L,10,60]
H, [J,20,50], [K,10,70], [L,10,60]
I, [J,15,50], [K,10,70], [L,7,60]
J, [End,10,0]
K, [End,10,0]
L, [End,10,0]



