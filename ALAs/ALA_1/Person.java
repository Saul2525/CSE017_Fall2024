/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public abstract class Person {
    // Data Members of the class Person.
    protected int id;
    protected String name, address, phone, email;

    // Default Constructor of the class Person.
    protected Person() {
        this(0, "None", "None", "None", "None");
    }

    // A Constructor with five Parameters of the class Person.
    protected Person(int id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    // Returns a Person's ID.
    public int getID() {
        return id;
    }

    // Returns a Person's Name.
    public String getName() {
        return name;
    }

    // Returns a Person's Address.
    public String getAddress() {
        return address;
    }

    // Returns a Person's Phone Number.
    public String getPhone() {
        return phone;
    }

    // Returns a Person's Email.
    public String getEmail() {
        return email;
    }

    // Returns a String that contains a Person's ID, Name, Address, Phone Number, and Email.
    public String toString() {
        return String.format("ID: %d\nName: %s\nAddress: %s\nPhone: %s\nEmail: %s\n", id, name, address, phone, email);
    }

    // Sets a Person's ID.
    public void setID(int id) {
        this.id = id;
    }

    // Sets a Person's Name.
    public void setName(String name) {
        this.name = name;
    }

    // Sets a Person's Address.
    public void setAdress(String address) {
        this.address = address;
    }

    // Sets a Person's Phone Number.
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Sets a Person's Email.
    public void setEmail(String email) {
        this.email = email;
    }
}