package shelter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shelter {
    private List<Animal> data;
    private int capacity;

    public Shelter(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>();
    }
    public void add(Animal animal){
        if (getCount() < capacity){
            data.add(animal);
        }
    }
    public boolean remove (String name){
        Animal animal = data.stream().filter(animal1 -> animal1.getName().equals(name)).findFirst().orElse(null);
        if (animal != null){
            data.remove(animal);
            return true;
        }
        return false;
    }

    public Animal getAnimal(String name, String caretaker){
        return data.stream().filter(animal -> animal.getName().equals(name)
                && animal.getCaretaker().equals(caretaker)).findFirst().orElse(null);
    }
    public Animal getOldestAnimal (){
        return data.stream().max(Comparator.comparing(Animal::getAge)).orElse(null);
    }
    public int getCount(){
        return data.size();
    }
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The shelter has the following animals:");
        data.forEach(animal -> sb.append(System.lineSeparator()).append(animal.getName()).append(" ").append(animal.getCaretaker()));
        return sb.toString().trim();
    }



}
