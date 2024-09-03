/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Faculty extends Employee {
    // A Data Member of the class Faculty.
    private String rank;

    // Default Constructor of the class Faculty.
    public Faculty() {
        super();
        rank = "None";
    }

    // A Constructor with eight Parameters of the class Faculty.
    public Faculty(int id, String name, String address, String phone, String email, String position, double salary, String rank) {
        super(id, name, address, phone, email, position, salary);
        this.rank = rank;
    }

    // Returns a Faculty's Rank.
    public String getRank() {
        return rank;
    }

    // Returns a String containing the information from both the Person and Employee classes, plus the Faculty's Rank.
    public String toString() {
        return (super.toString() + String.format("Rank: %s\n", rank));
    }

    // Sets a Faculty's Rank.
    public void setRank(String rank) {
        this.rank = rank;
    }
}