/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Faculty extends Employee {
    private String rank;

    public Faculty() {
        super();
        rank = "None";
    }

    public Faculty(int id, String name, String address, String phone, String email, String position, double salary, String rank) {
        super(id, name, address, phone, email, position, salary);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public String toString() {
        return (super.toString() + String.format("Rank: %s\n", rank));
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}