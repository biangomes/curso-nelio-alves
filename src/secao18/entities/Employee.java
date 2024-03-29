package secao18.entities;

public class Employee implements Comparable<Employee>{
    private String name;
    private Double salary;

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // Metodo necessario para utilizar o metodo sort() de Collections
    @Override
    public int compareTo(Employee e) {
        //return name.compareTo(e.getName());       // comparando por name
        return salary.compareTo(e.getSalary());     // comparando por salary
    }
}
