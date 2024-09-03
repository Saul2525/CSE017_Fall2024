/*
    Saul Toribio
    9/2/24
    CSE017 Fall 2024: ALA 1
    IDE: VSCode; JDK: 11
*/

public class Test {
    // A Method that prints the content of array people, unsorted.
    public static void printArray(Person[] people) {
        for (Person p : people) {
            System.out.println(p);
        }
    }
    
    // A Method that prints the content of array people, sorted by Name or ID.
    public static void sortArray(Person[] people, boolean type) {
        for (int i = 0; i < people.length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < people.length; j++) {
                if (type) {
                    if (people[j].getName().compareTo(people[minIndex].getName()) < 0) {
                        minIndex = j;
                    }
                } else {
                    if (people[j].getID() < people[minIndex].getID()) {
                        minIndex = j;
                    }
                }
            }

            Person temp = people[i];
            people[i] = people[minIndex];
            people[minIndex] = temp;
            System.out.println(people[i]);
        }
    }

    public static void main(String[] args) {
        // Creates an array called people with a length of 3.
        Person[] people = new Person[3];

        // Fills out information using the three extended classes.
        people[0] = new Student(123456789, "Paul Leister", "972 4th Street, Allentown", "610-331-7177", "pleister@gmail.com", "CSE");
        people[1] = new Employee(334422110, "Beth Down", "234 Main Street, Philadelphia", "484-222-4433", "bdown@gmail.com", "System Administrator", 75000.00);
        people[2] = new Faculty(222222222, "Mark Jones", "21 Orchid Street, Bethlehem", "610-333-2211", "mjones@gmail.com", "Faculty", 100000.00, "Associate Professor");

        // Prints the unsorted array.
        System.out.println("Original List:");
        printArray(people);

        // Prints a sorted array by Name.
        System.out.println("List sorted by name:");
        sortArray(people, true);

        // Prints a sorted array by ID.
        System.out.println("List sorted by id:");
        sortArray(people, false);
    }
}