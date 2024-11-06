/*
    Saul Toribio
    10/25/24
    CSE017 Fall 2024: PA 6
    IDE: VSCode; JDK: 11
*/

import java.util.LinkedList;

/**
 * A class that holds some basic information about a Olympic athlete.
*/
public class Athlete implements Comparable<Athlete> {
    private int id;
    private String name;
    private String country;
    private LinkedList<Pair<Integer, Triple<Integer, Integer, Integer>>> medals;

    /**
     * Creates a athlete. Default configuration.
     * Time complexity: O(1).
     * @param id The athlete's ID.
     * @param name The athlete's name.
     * @param country The athlete's country.
    */
    public Athlete(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.medals = new LinkedList<>();
    }

    /**
     * Gets the athlete's ID.
     * Time complexity: O(1).
     * @return The athlete's ID.
    */
    public int getID() {
        return id;
    }

    /**
     * Gets the athlete's name.
     * Time complexity: O(1).
     * @return The athlete's name.
    */
    public String getName() {
        return name;
    }

    /**
     * Gets the athlete's country.
     * Time complexity: O(1).
     * @return The athlete's country.
    */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the medal count throughout an athlete's career.
     * Time complexity: O(n).
     * @return The total amount of medals an athlete has received during their entire career.
    */
    public int getTotalMedals() {
        int totalMedals = 0;
        for (Pair<Integer, Triple<Integer, Integer, Integer>> pair : medals) {
            Triple<Integer, Integer, Integer> tempMedals = pair.getSecond();
            totalMedals += (tempMedals.getFirst() + tempMedals.getSecond() + tempMedals.getThird());
        }
        return totalMedals;
    }

    /**
     * Gets the medal count during the specified year.
     * Time complexity: O(n).
     * @param year The year looked for.
     * @return The medal count during the specified year.
    */
    public int getTotalMedals(int year) {
        for (Pair<Integer, Triple<Integer, Integer, Integer>> pair : medals) {
            if (pair.getFirst().equals(year)) {
                Triple<Integer, Integer, Integer> tempMedals = pair.getSecond();
                return (tempMedals.getFirst() + tempMedals.getSecond() + tempMedals.getThird());
            }
        }
        return 0;
    }

    /**
     * Sets the athlete's ID.
     * Time complexity: O(1).
     * @param id The athlete's ID.
    */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Sets the athlete's name.
     * Time complexity: O(1).
     * @param name The athlete's name.
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the athlete's country.
     * Time complexity: O(1).
     * @param country The athlete's country.
    */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Adds a medal to the specified year list. Otherwise, creates a new year list.
     * Time complexity: O(n).
     * @param year The year a medal is added to.
     * @param type The type of metal that the athlete earned. Either Gold, Silver or Bronze.
    */
    public void addMedal(int year, int type) {
        for (Pair<Integer, Triple<Integer, Integer, Integer>> pair : medals) {
            if (pair.getFirst().equals(year)) {
                Triple<Integer, Integer, Integer> tempMedals = pair.getSecond();
                switch (type) {
                    case 0:
                        tempMedals.setFirst(tempMedals.getFirst() + 1);
                        break;
                    case 1:
                        tempMedals.setSecond(tempMedals.getSecond() + 1);
                        break;
                    case 2:
                        tempMedals.setThird(tempMedals.getThird() + 1);
                        break;
                }
                return;
            }
        }

        Triple<Integer, Integer, Integer> tempMedals = new Triple<>(0, 0, 0);
        switch (type) {
            case 0:
                tempMedals.setFirst(1);
                break;
            case 1:
                tempMedals.setSecond(1);
                break;
            case 2:
                tempMedals.setThird(1);
                break;
        }
        medals.add(new Pair<>(year, tempMedals));
    }

    /**
     * Prints the athlete's ID, name, country, and total medal count during their career.
     * Time complexity: O(1).
    */
    public String toString() {
        return String.format("%d\t%-50s\t%s\t%d", id, name, country, getTotalMedals());
    }

    /**
     * Compares the total medal count of two athletes.
     * Time complexity: O(1).
    */
    public int compareTo(Athlete a) {
        return Integer.compare(this.getTotalMedals(), a.getTotalMedals());
    }

    /**
     * Matches the IDs of two athletes.
     * Time complexity: O(1).
    */
    @Override public boolean equals(Object o) {
        Athlete a = (Athlete) o;
        return (this.getID() == a.getID());
    }
}