package sanctuary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Habitat {
    private List<Elephant> data;
    private int capacity;

    public Habitat(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public void add(Elephant elephant) {
        if (data.size() < capacity) {
            data.add(elephant);
        }
    }

    public boolean remove(String name) {
        Elephant elephant = data.stream().filter(elephant1 -> elephant1.getName().equals(name)).findFirst().orElse(null);
        if (data.contains(elephant)) {
            data.remove(elephant);
            return true;
        }
        return false;
    }

    public Elephant getElephant(String retiredFrom) {
        return data.stream().filter(elephant -> elephant.getRetiredFrom().equals(retiredFrom)).findFirst().orElse(null);
    }

    public Elephant getOldestElephant() {
        return data.stream().max(Comparator.comparing(Elephant::getAge)).orElse(null);

    }

    public int getAllElephants() {
        return data.size();
    }

    public String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Saved elephants in the park:").trimToSize();
        data.forEach(elephant -> sb.append(System.lineSeparator())
                .append(elephant.getName())
                .append(" came from: ")
                .append(elephant.getRetiredFrom()));
        return sb.toString().trim();
    }
}
