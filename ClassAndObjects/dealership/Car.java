package dealership;

public class Car {
    public String manufacturer;

    public String model;

    public int year;

    public int getYear() {
        return year;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Car(String manufacturer, String model, int year) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }
    @Override
    public String toString(){
        return String.format("%s %s (%d)", this.manufacturer, this.model, this.year);
    }
}
