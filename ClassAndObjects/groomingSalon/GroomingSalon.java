package groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private List<Pet> data;
    private int capacity;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>();
    }
    public void add(Pet pet){
        if (data.size() < capacity){
            data.add(pet);
        }
    }
    public boolean remove(String name){
        Pet pet = data.stream().filter(pet1 -> pet1.getName().equals(name)).findFirst().orElse(null);
        if (pet != null){
            data.remove(pet);
            return true;
        }
        return false;
    }
    public Pet getPet(String name, String owner){
        return data.stream().filter(pet -> pet.getName().equals(name) && pet.getOwner().equals(owner)).findFirst().orElse(null);
    }
    public int getCount(){
        return data.size();
    }
    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
//        "The grooming salon has the following clients:\n" +
//                "{name} {owner}\n"

        sb.append("The grooming salon has the following clients:");
        data.forEach(pet -> sb.append(System.lineSeparator()).append(pet.getName()).append(" ").append(pet.getOwner()));
        return sb.toString().trim();
    }

}
