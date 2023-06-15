package hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    public List<Person> roster;
    private String name;
    private int capacity;


    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        roster = new ArrayList<>();
    }
    public void add (Person person){
        if (getCount() < capacity){
            roster.add(person);
        }
    }
    public boolean remove(String name){
        Person person = roster.stream().filter(person1 -> person1.getName().equals(name)).findFirst().orElse(null);
        if (person != null){
            roster.remove(person);
            return true;
        }
        return false;
    }
    public Person getPerson(String name, String hometown){
        return roster.stream().filter(person1 -> person1.getName().equals(name)&& person1.getHometown().equals(hometown)).findFirst().orElse(null);
    }
    public int getCount(){
        return roster.size();
    }
    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
//        "The people in the hotel {name} are:\n" +
//                "{Person1}\n"
        sb.append(String.format("The people in the hotel %s are:", this.name));
        roster.forEach(person -> sb.append(System.lineSeparator()).append(person.toString()));
        return sb.toString().trim();
    }
}
