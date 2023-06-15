package parrots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Parrot parrot) {
        if (count() < capacity) {
            data.add(parrot);
        }
    }

    public boolean remove(String name) {
        Parrot parrot1 = data.stream().filter(parrot -> parrot.getName().equals(name)).findFirst().orElse(null);

        for (Parrot parrot : data) {
            if (parrot1 != null) {
                data.remove(parrot);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String name) {
        return data.stream().filter(parrot -> parrot.getName().equals(name)).findFirst().map(parrot -> {
            parrot.setAvailable(false);
            return parrot;
        }).orElse(null);
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        return data.stream()
                .filter(parrot -> parrot.getSpecies().equals(species))
                .peek(parrot -> parrot.setAvailable(false))
                .collect(Collectors.toList());
    }

    public int count() {
        return data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Parrots available at %s:", this.name));

        data.stream()
                .filter(Parrot::isAvailable)
                .forEach(parrot -> sb.append(System.lineSeparator()).append(parrot.toString()));

        return sb.toString().trim();
    }


}
