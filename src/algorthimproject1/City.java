package algorthimproject1;

import java.util.ArrayList;
import java.util.List;

public class City {

    String name;
    List<Neighbor> neighbors;

    public City(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "name=" + name + ", neighbors=" + neighbors + '}';
    }
    
}
