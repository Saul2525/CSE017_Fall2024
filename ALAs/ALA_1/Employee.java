/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Employee extends Person {
    // Data Members of the class Employee.
    private String position;
    private double salary;

    // Default Constructor of the class Employee.
    public Employee() {
        super();
        position = "None";
        salary = 0.0;
    }

    // A Constructor with seven Parameters of the class Employee.
    public Employee(int id, String name, String address, String phone, String email, String position, double salary) {
        super(id, name, address, phone, email);
        this.position = position;
        this.salary = salary;
    }

    // Returns a Employee's Position.
    public String getPosition() {
        return position;
    }

    // Returns a Employee's Salary.
    public double getSalary() {
        return salary;
    }

    // Returns a String containing the information from the class Person and the Employee's Position and Salary.
    public String toString() {
        return (super.toString() + String.format("Position: %s\nAnnual salary: $%.2f\n", position, salary));
    }

    // Sets a Employee's Position.
    public void setPosition(String position) {
        this.position = position;
    }

    // Sets a Employee's Salary.
    public void setSalary(double salary) {
        this.salary = salary;
    }
}