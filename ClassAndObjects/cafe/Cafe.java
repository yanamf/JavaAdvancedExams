package cafe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cafe {
    private List<Employee> employees;

    private String name;

    private int capacity;

    public Cafe(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addEmployee(Employee employee) {
        if (employees.size() < capacity) {
            employees.add(employee);
        }
    }

    public boolean removeEmployee(String name) {
        Employee employee = employees.stream().filter(employee1 -> employee1.getName().equals(name)).findFirst().orElse(null);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;
    }
    public Employee getOldestEmployee(){
        return employees.stream().max(Comparator.comparing(Employee::getAge)).orElse(null);
    }
    public Employee getEmployee(String name){
        return employees.stream().filter(employee -> employee.getName().equals(name)).findFirst().orElse(null);
    }
    public int getCount(){
        return employees.size();
    }
    public String report(){
        StringBuilder sb = new StringBuilder();
//        "Employees working at Cafe {cafeName}:\n{Employee1}\n"
        sb.append(String.format("Employees working at Cafe %s:", this.name)).trimToSize();
        employees.forEach(employee -> sb.append(System.lineSeparator()).append(employee.toString()));
        return sb.toString().trim();
    }
}
