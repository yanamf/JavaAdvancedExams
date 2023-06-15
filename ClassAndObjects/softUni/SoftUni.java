package softUni;

import java.util.ArrayList;
import java.util.List;

public class SoftUni {
    private int capacity;

    public List<Student> getData() {
        return data;
    }

    private List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return data.size();
    }

    public String insert(Student student) {
        if (getCount() < capacity) {
            if (!data.contains(student)){
                data.add(student);
                return String.format("Added student %s %s", student.getFirstName(), student.getLastName());
            } else {
                return "Student is already in the hall.";
            }
        } else {
            return "The hall is full.";
        }
    }
    public String remove(Student student){
        if (data.contains(student)){
            data.remove(student);
            return String.format("Removed student %s %s.", student.getFirstName(), student.getLastName());
        } else {
            return "Student not found.";
        }
    }
    public Student getStudent(String firstName, String lastName){
        return data.stream().filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)).findFirst().orElse(null);
    }
    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hall size: %d\n", getCount()));
        data.forEach(student -> sb.append(String.format("Student: %s %s, Best Course = %s\n", student.getFirstName(), student.getLastName(), student.getBestCourse())));
        return sb.toString();
    }

}
