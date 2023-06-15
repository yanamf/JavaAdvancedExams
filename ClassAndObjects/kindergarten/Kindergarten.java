package kindergarten;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        registry = new ArrayList<>();
    }

    public boolean addChild(Child child) {
        if (registry.size() < capacity) {
            registry.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(String firstName) {
        Child child = getChild(firstName);
        if (registry.contains(child)) {
            registry.remove(child);
            return true;
        }
        return false;
    }

    public int getChildrenCount() {
        return registry.size();
    }

    public Child getChild(String firstName) {
        return registry.stream().filter(child -> child.getFirstName().equals(firstName)).findFirst().orElse(null);
    }

    public String registryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Registered children in %s:", name)).trimToSize();
        registry.stream()
                .sorted
                        (Comparator.comparing(Child::getAge)
                                .thenComparing(Child::getFirstName)
                                .thenComparing(Child::getLastName))
                .forEach(child -> {
                    sb.append(System.lineSeparator()).append("--").append(System.lineSeparator());
                    sb.append(child.toString());
                });

        return sb.toString().trim();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Child> getRegistry() {
        return registry;
    }

    public void setRegistry(List<Child> registry) {
        this.registry = registry;
    }
}
