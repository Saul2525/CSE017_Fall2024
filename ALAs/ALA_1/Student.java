/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Student extends Person {
    // A Data Member of the class Student.
    private String major;

    // Default Constructor of the class Student.
    public Student() {
        super();
        major = "Undeclared";
    }

    // A Constructor with six Parameters of the class Student.
    public Student(int id, String name, String address, String phone, String email, String major) {
        super(id, name, address, phone, email);
        this.major = major;
    }

    // Returns a Student's Major.
    public String getMajor() {
        return major;
    }

    // Returns a String containing the information from the class Person and the Student's Major.
    public String toString() {
        return (super.toString() + String.format("Major: %s\n", major));
    }

    // Sets a Student's Major.
    public void setMajor(String major) {
        this.major = major;
    }
}