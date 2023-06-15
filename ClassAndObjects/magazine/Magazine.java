package magazine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Magazine {
    private List<Cloth> data;
    private String type;
    private int capacity;

    public Magazine(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public void addCloth(Cloth cloth) {
        if (data.size() < capacity) {
            data.add(cloth);
        }
    }

    public Cloth getCloth(String color) {
        return data.stream().filter(cloth -> cloth.getColor().equals(color)).findFirst().orElse(null);

    }

    public boolean removeCloth(String color) {
        Cloth cloth = getCloth(color);
        data.remove(cloth);
        return cloth != null;
    }

    public Cloth getSmallestCloth() {
        return data.stream().min(Comparator.comparing(Cloth::getSize)).orElse(null);
    }

    public int getCount() {
        return data.size();
    }
    public String report(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s magazine contains:\n", type)).trimToSize();
        //append the list data to my sb
        data.forEach(clothString -> sb.append(clothString).append(String.format("%n")));
        return sb.toString().trim();
    }

}
