package aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private String name;
    private int capacity;
    private int size;
    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return this.fishInPool.size();
    }

    public void add(Fish fish){
        if (this.fishInPool.size() < capacity){
            if (!this.fishInPool.contains(fish.getName())){
                this.fishInPool.add(fish);
            }
        }
    }
    public boolean remove(String name){
        Fish fish = findFish(name);
        if (fish != null){
            fishInPool.remove(fish);
            return true;
        }
        return false;
    }
    public Fish findFish(String name){
        return fishInPool.stream().filter(fish1 -> fish1.getName().equals(name)).findFirst().orElse(null);
    }
    public String report(){
//        "{size}\n" +
//                "{Fish1}\n"
        StringBuilder sb = new StringBuilder();
        sb.append("Aquarium: ").append(this.name).append(" ^ Size: ")
                .append(this.size).append(System.lineSeparator()).trimToSize();
        fishInPool.forEach(fish -> sb.append(fish.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
