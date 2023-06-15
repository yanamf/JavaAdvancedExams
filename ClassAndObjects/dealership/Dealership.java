package dealership;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dealership {
    public List<Car> data;

    public String name;

    public int capacity;

    public String getName() {
        return name;
    }

    public Dealership(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public void add(Car car) {
        if (data.size() < capacity) {
            data.add(car);
        }
    }

    public boolean buy(String manufacturer, String model) {
        return data.removeIf(car1 -> car1.getManufacturer().equals(manufacturer) && car1.getModel().equals(model));
    }

    public Car getLatestCar() {
        return data.stream().max(Comparator.comparing(Car::getYear)).orElse(null);
    }

    public Car getCar(String manufacturer, String model) {
        return data.stream().filter(car -> car.getManufacturer().equals(manufacturer) && car.getModel().equals(model)).findFirst().orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("The cars are in a car dealership %s:", getName()));
        data.forEach(car -> sb.append(System.lineSeparator()).append(car.toString()));
        return sb.toString().trim();

    }
}
