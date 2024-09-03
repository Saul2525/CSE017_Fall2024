/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Employee extends Person {
    private String position;
    private double salary;

    public Employee() {
        super();
        position = "None";
        salary = 0.0;
    }

    public Employee(int id, String name, String address, String phone, String email, String position, double salary) {
        super(id, name, address, phone, email);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return (super.toString() + String.format("Position: %s\nAnnual salary: $%.2f\n", position, salary));
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}