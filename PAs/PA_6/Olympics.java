/*
    Saul Toribio
    10/25/24
    CSE017 Fall 2024: PA 6
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A class contains a list of athletes and countries.
*/
public class Olympics {
    private ArrayList<Athlete> athletes;
    private ArrayList<String> countries;

    /**
     * Creates two lists that contains athletes and countries respectively by reading a .csv and .txt files.
     * Time complexity: O(n(n + m)).
     * @param athleteFilename The name of the file that contains the list of athletes.
     * @param countryFilename The name of the file that contains the list of countries.
    */
    public Olympics(String athleteFilename, String countryFilename) {
        athletes = new ArrayList<>();
        countries = new ArrayList<>();

        int count = 0;
        try (Scanner readcsv = new Scanner(new File(athleteFilename))) {
            readcsv.nextLine();
            while (readcsv.hasNextLine()) {
                String[] tempString = readcsv.nextLine().split(",");
                Athlete tempAthlete = new Athlete(Integer.parseInt(tempString[0].trim()), tempString[1], tempString[2]);
                Athlete existingAthlete = find(tempAthlete);

                System.out.println(count++);
                addMedal(existingAthlete, tempAthlete, Integer.parseInt(tempString[3].trim()), tempString[4]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("athletes.csv not found.");
        }

        try (Scanner readtxt = new Scanner(new File(countryFilename))) {
            while (readtxt.hasNextLine()) {
                String tempCountry = readtxt.nextLine();
                countries.add(tempCountry);
            }
        } catch (FileNotFoundException e) {
            System.out.println("countries.txt not found.");
        }
    }

    /**
     * This a helper method that I added when it's time to add medals to athletes.
     * Time complexity: O(n).
     * @param existingAthlete Assuming that we found the same athlete in the list of athletes, then we use this athlete variable.
     * @param tempAthlete The athlete located within a line of the .csv file.
     * @param year The year to which the medal is to be added.
     * @param medalType The type of medal won. Either Gold, Silver, or Bronze. Could also be blank.
    */
    public void addMedal(Athlete existingAthlete, Athlete tempAthlete, int year, String medalType) {
        int type = -1;
        switch (medalType) {
            case "Gold":
                type = 0;
                break;
            case "Silver":
                type = 1;
                break;
            case "Bronze":
                type = 2;
                break;
        }

        if (existingAthlete != null) {
            existingAthlete.addMedal(year, type);
        } else {
            tempAthlete.addMedal(year, type);
            athletes.add(tempAthlete);
        }
    }

    /**
     * Adds a athlete to the athletes list.
     * Time complexity: O(n).
     * @param a A athlete.
    */
    public void add(Athlete a) {
        if (!(athletes.contains(a))) {
            athletes.add(a);
        }
    }

    /**
     * Checks if an athlete is present in the athletes list.
     * Time complexity: O(n).
     * @param a A athlete.
     * @return If the athlete is found, then the athlete is returned. Otherwise, null is returned.
    */
    public Athlete find(Athlete a) {
        for (Athlete athlete : athletes) {
            if ((athlete != null) && (athlete.equals(a))) {
                return athlete;
            }
        }
        return null;
    }

    /**
     * Removes a athlete from the athletes list.
     * Time complexity: O(n).
     * @param a A athlete.
     * @return If the athlete is found, then the athlete is removed and returned. Otherwise, null is returned.
    */
    public Athlete remove(Athlete a) {
        Athlete existingAthlete = find(a);
        if (existingAthlete != null) {
            athletes.remove(existingAthlete);
            return existingAthlete;
        }
        return null;
    }

    /**
     * Gets the size of the athletes list.
     * Time complexity: O(1).
     * @return The size of the athletes list.
    */
    public int getAthleteCount() {
        return athletes.size();
    }

    /**
     * Gets the size of the countries list.
     * Time complexity: O(1).
     * @return The size of the countries list.
    */
    public int getCountryCount() {
        return countries.size();
    }

    /**
     * Prints the top ten medaled athletes in this history of the Olympics.
     * Time complexity: O(n log n).
    */
    public void viewTopTenAthletes() {
        ArrayList<Athlete> topTenAthletes = new ArrayList<>(athletes);
        topTenAthletes.sort(new Comparator<Athlete>() {
            public int compare(Athlete a1, Athlete a2) {
                if (a2.compareTo(a1) != 0) {
                    return a2.compareTo(a1);
                }
                return Integer.compare(a2.getID(), a1.getID());
            }
        });

        System.out.printf("Top Ten Medaled Athletes in the history of the Olympics\n");
        for (int i = 0; i < Math.min(10, topTenAthletes.size()); i++) {
            System.out.println(topTenAthletes.get(i).toString());
        }
    }

    /**
     * Prints the top ten medaled athletes during a specific year.
     * Time complexity: O(n m log n).
     * @param year The year checked for medals.
    */
    public void viewTopTenAthletes(int year) {
        ArrayList<Athlete> topTenAthletes = new ArrayList<>(athletes);
        topTenAthletes.sort(new Comparator<Athlete>() {
            public int compare(Athlete a1, Athlete a2) {
                if (Integer.compare(a2.getTotalMedals(year), a1.getTotalMedals(year)) != 0) {
                    return Integer.compare(a2.getTotalMedals(year), a1.getTotalMedals(year));
                }
                return Integer.compare(a2.getID(), a1.getID());
            }
        });

        System.out.printf("Top Ten Medaled Athletes in the Olympics %d\n", year);
        for (int i = 0; i < Math.min(10, topTenAthletes.size()); i++) {
            int id = topTenAthletes.get(i).getID();
            String name = topTenAthletes.get(i).getName();
            String country = topTenAthletes.get(i).getCountry();
            int medals = topTenAthletes.get(i).getTotalMedals(year);
            System.out.printf("%d\t%-50s\t%s\t%d\n", id, name, country, medals);
        }
    }

    /**
     * Prints the top ten medaled countries in the history of the Olympics.
     * Time complexity: O(c * n * m).
    */
    public void viewTopTenCountries() {
        ArrayList<String> topTenCountries = new ArrayList<>();
        for (String country : countries) {
            int totalMedals = 0;
            for (Athlete athlete : athletes) {
                if (athlete.getCountry().equals(country)) {
                    totalMedals += athlete.getTotalMedals();
                }
            }

            if (totalMedals > 0) {
                topTenCountries.add(country + "," + totalMedals);
            }
        }

        topTenCountries.sort(new Comparator<String>() {
            public int compare(String c1, String c2) {
                int m1 = Integer.parseInt(c1.split(",")[1].trim());
                int m2 = Integer.parseInt(c2.split(",")[1].trim());
                return Integer.compare(m2, m1);
            }
        });

        System.out.printf("Top Ten Medaled Countries in the history of the Olympics\n");
        for (int i = 0; i < Math.min(10, topTenCountries.size()); i++) {
            String country = topTenCountries.get(i).split(",")[0];
            int medals = Integer.parseInt(topTenCountries.get(i).split(",")[1].trim());
            System.out.printf("%s\t%d\n", country, medals);
        }
    }

    /**
     * Prints the top ten medaled countries during a specific year.
     * Time complexity: O(c * n * m).
     * @param year The year checked for medals.
    */
    public void viewTopTenCountries(int year) {
        ArrayList<String> topTenCountries = new ArrayList<>();
        for (String country : countries) {
            int totalMedals = 0;
            for (Athlete athlete : athletes) {
                if (athlete.getCountry().equals(country)) {
                    totalMedals += athlete.getTotalMedals(year);
                }
            }
            topTenCountries.add(country + "," + totalMedals);
        }

        topTenCountries.sort(new Comparator<String>() {
            public int compare(String c1, String c2) {
                int m1 = Integer.parseInt(c1.split(",")[1].trim());
                int m2 = Integer.parseInt(c2.split(",")[1].trim());
                return Integer.compare(m2, m1);
            }
        });

        System.out.printf("Top Ten Medaled Countries in the Olympics %d\n", year);
        for (int i = 0; i < Math.min(10, topTenCountries.size()); i++) {
            String country = topTenCountries.get(i).split(",")[0];
            int medals = Integer.parseInt(topTenCountries.get(i).split(",")[1].trim());
            System.out.printf("%s\t%d\n", country, medals);
        }
    }
}