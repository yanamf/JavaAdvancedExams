package university;


import java.util.ArrayList;
import java.util.List;

public class University {
    public List<Student> students;
    public int capacity;


    public List<Student> getStudents() {
        return students;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStudentCount() {
        return students.size();
    }

    public String registerStudent(Student student) {
        if (students.contains(student)){
            return "Student is already in the university";
        } else if (getStudentCount() < capacity) {
            students.add(student);
            return String.format("Added student %s %s", student.getFirstName(), student.getLastName());
        } else {
            return "No seats in the university";
        }
    }
    public String dismissStudent(Student student){
        if (students.contains(student)){
            students.remove(student);
            return String.format("Removed student %s %s", student.getFirstName(), student.getLastName());
        } else {
            return "Student not found";
        }
    }
    public Student getStudent(String firstName, String lastName){
        return students.stream().filter(student -> student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)).findFirst().orElse(null);
    }
    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
//        "==Student: First Name = {firstName}, Last Name = {lastName}, Best Subject = {bestSubject} "
        students.forEach(student -> sb.append(String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s", student.getFirstName(), student.getLastName(), student.getBestSubject())).append(System.lineSeparator()));
        return sb.toString().trim();
    }

    public University(int capacity) {
        this.capacity = capacity;
        students = new ArrayList<>();
    }
}
