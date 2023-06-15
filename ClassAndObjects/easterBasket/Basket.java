package easterBasket;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Basket {
    private List<Egg> data;
    private String material;
    private int capacity;

    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        data = new LinkedList<>();
    }
    public void addEgg(Egg egg){
        if (getCount() < capacity){
            data.add(egg);
        }
    }
    public boolean removeEgg(String color){
        Egg egg = getEgg(color);
        if (data.contains(egg)){
            data.remove(egg);
            return true;
        }
        return false;
    }
    public Egg getStrongestEgg(){
        return data.stream().max(Comparator.comparing(Egg::getStrength)).stream().findFirst().orElse(null);
    }
    public Egg getEgg(String color){
        return data.stream().filter(egg -> egg.getColor().equals(color)).findFirst().orElse(null);
    }
    public int getCount(){
        return data.size();
    }
    public String report(){
        StringBuilder sb = new StringBuilder();
//        "{material} basket contains:\n
//        {Egg1}\n
        sb.append(this.material).append(" basket contains:");
        data.forEach(egg -> sb.append(System.lineSeparator()).append(egg));
        return sb.toString();
    }


}
