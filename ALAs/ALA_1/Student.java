/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Student extends Person {
    private String major;

    public Student() {
        super();
        major = "Undeclared";
    }

    public Student(int id, String name, String address, String phone, String email, String major) {
        super(id, name, address, phone, email, major);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public String toString() {
        return (super.toString() + "Major: " + major + "\n");
    }

    public void setMajor(String major) {
        this.major = major;
    }
}