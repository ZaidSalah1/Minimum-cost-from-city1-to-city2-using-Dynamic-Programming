package algorthimproject1;

public class Neighbor {

    String city;
    int petrolCost;
    int hotelCost;

    public Neighbor(String city, int petrolCost, int hotelCost) {
        this.city = city;
        this.petrolCost = petrolCost;
        this.hotelCost = hotelCost;
    }
    
       @Override
    public String toString() {
        return "Neighbor{" +
                "city='" + city + '\'' +
                ", petrolCost=" + petrolCost +
                ", hotelCost=" + hotelCost +
                '}';
    }
}
