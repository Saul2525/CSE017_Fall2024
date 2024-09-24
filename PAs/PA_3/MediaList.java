/*
    Saul Toribio
    9/23/24
    CSE017 Fall 2024: PA 3
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * A class storing a set of media objects from the class Media.
*/
public class MediaList {
    private Media[] media;
    private int count;
    private Archive archive;

    /**
     * Creates a media list. Default configuration.
    */
    public MediaList() {
        media = new Media[10];
        count = 0;
        archive = new Archive();
    }

    /**
     * Creates a media list. It's information is filled out by a .txt file.
     * @param filename The .txt file's name.
    */
    public MediaList(String filename) {
        media = new Media[50];
        count = 0;
        archive = new Archive();

        read(filename);
    }

    /**
     * Reads the information within the .txt file and adds it to the media list.
     * @param filename The .txt file's name.
    */
    private void read(String filename) {
        try {
            Scanner readFile = new Scanner(new File(filename));

            while (readFile.hasNextLine()) {
                String[] line = readFile.nextLine().split(",");

                String title = line[1];
                String category = line[2];
                String tempYear = line[3];
                String tempSize = line[4];

                int year = Integer.parseInt(tempYear);
                long size = Integer.parseInt(tempSize);
                
                if (line[0].equals("Movie")) {
                    String director = line[5];
                    long revenue = Long.parseLong(line[6]);

                    media[count++] = new Movie(title, category, year, size, director, revenue);
                    
                    if ((line.length == 8) && (line[7].equals((String) "AR"))) {
                        this.archive.add((Archivable) media[count - 1]);
                    }
                } else if (line[0].equals("Song")) {
                    String artist = line[5];

                    media[count++] = new Song(title, category, year, size, artist);

                    if ((line.length == 7) && (line[6].equals((String) "AR"))) {
                        this.archive.add((Archivable) media[count - 1]);
                    }
                }
            }

            readFile.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Saves the information within the media list to a .txt file.
     * @param filename The .txt file's name.
    */
    public void save(String filename) {
        try {
            PrintWriter writerFile = new PrintWriter(new File(filename));

            for (Media m : media) {
                if (m != null) {
                    if (archive.find((Archivable) m)) {
                        writerFile.print(m.fileToString() + ",AR\n");
                    } else {
                        writerFile.print(m.fileToString() + "\n");
                    }
                }
            }

            writerFile.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Adds a media object to the media list.
     * @param media The media object. Either a movie or a song.
     * @return True if the media object was added to media list. False otherwise.
    */
    public boolean add(Media media) {
        if (count < 10) {
            this.media[count++] = media;
            return true;
        }

        return false;
    }

    /**
     * Returns a media object after matching the inputted title.
     * @param title The title to be matched with a media object within the media list.
     * @return The media object that matched the inputted title. Null otherwise.
    */
    public Media findTitle(String title) {
        for (int i = 0; i < count; i++) {
            if (media[i].getTitle().equals(title)) {
                return media[i];
            }
        }

        return null;
    }

    /**
     * Returns a media list matching the inputted category.
     * @param category The category to be matched with media objects of the same category within the media list.
     * @return A media list containing media objects with the matching category. Null otherwise.
    */
    public Media[] findCategory(String category) throws InvalidMediaAttribute {
        checkCategory(category);
        int categoryCount = 0;

        for (int i = 0; i < count; i++) {
            if (media[i].getCategory().equals(category)) {
                categoryCount++;
            }
        }

        if (categoryCount != 0) {
            Media[] tempMedia = new Media[categoryCount];
            int tempCount = 0;

            for (int i = 0; i < count; i++) {
                if (media[i].getCategory().equals(category)) {
                    tempMedia[tempCount] = media[i];
                    tempCount++;
                }
            }

            return tempMedia;
        }

        return null;
    }

    /**
     * Returns a media list matching the inputted year.
     * @param year The year to be matched with media objects of the same year within the media list.
     * @return A media list containing media objects with the matching year. Null otherwise.
    */
    public Media[] findYear(int year) throws InvalidMediaAttribute {
        checkYear(year);
        int yearCount = 0;

        for (int i = 0; i < count; i++) {
            if (media[i].getYear() == year) {
                yearCount++;
            }
        }

        if (yearCount != 0) {
            Media[] tempMedia = new Media[yearCount];
            int tempCount = 0;
    
            for (int i = 0; i < count; i++) {
                if (media[i].getYear() == year) {
                    tempMedia[tempCount] = media[i];
                    tempCount++;
                }
            }

            return tempMedia;
            }

        return null;
    }

    /**
     * Removes a media object from the media list.
     * @param title The title to be matched with a media object within the media list.
     * @return True if the media object was removed from the media list. False otherwise.
    */
    public boolean remove(String title) {
        for (int k = 0; k < count; k++) {
            if (media[k].getTitle().equals(title)) {
                for (int j = k; j < count - 1; j++) {
                    media[j] = media[j + 1];
                }
                media[count - 1] = null;
                count--;

                if (this.archive.find((Archivable) media[k])) {
                    this.archive.remove((Archivable) media[k]);
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Sorts and prints the media list by name or by category.
     * @param sortType True to sort the media list by name. False to sort it by category.
    */
    public void sort(boolean sortType) {
        if (sortType) {
            java.util.Arrays.sort(media, 0, count);
        } else {
            for (int i = 0; i < count; i++) {
                Media currentMedia = media[i];
                int j = i;

                while ((j > 0) && (currentMedia.getCategory().compareTo(media[j - 1].getCategory()) < 0)) {
                    media[j] = media[j - 1];
                    j--;
                }

                media[j] = currentMedia;
                media[j].toString();
            }
        }
    }

    /**
     * @return Returns the amount of media objects within the media list.
    */
    public int size() {
        return count;
    }

    /**
     * Combines the content of the media list into one string.
     * @return A string that contains the content of the media list.
    */
    public String toString() {
        System.out.println("Type\tTitle\t\t\t\t\t\t\t\tCategory\t\tYear\tSize(KB)\tArtist/Director\tRevenue");
        String tempString = "";

        for (int i = 0; i < count; i++) {
            tempString += media[i].toString() + "\n";
        }

        return tempString;
    }

    /**
     * Checks if the category inputted is valid.
     * @param c The name of the category.
     * @throws InvalidMediaAttribute If the inputted String does not match any valid categories.
    */
    private void checkCategory(String c) throws InvalidMediaAttribute {
        List<String> validCategories = Arrays.asList("Drama", "Horror", "Action", "Comedy", "Crime", "Supernatural", "Pop", "Rock", "Pop Rock", "Country", "Rap", "Jazz", "Mix");

        if (!(validCategories.contains(c))) {
            throw new InvalidMediaAttribute(String.format("Error: the media category %s is not a valid category", c));
        }
    }

    /**
     * Checks if the year inputted is valid.
     * @param y The year inputted.
     * @throws InvalidMediaAttribute If the inputted int is less than 1900 or greater then the current year.
    */
    private void checkYear(int y) throws InvalidMediaAttribute {
        int currentYear = LocalDate.now().getYear();

        if ((y < 1900) || (y > currentYear)) {
            throw new InvalidMediaAttribute("Error: the media year must be between 1900 and 2024");
        }
    }

    /**
     * Checks the amount of media objects that are within the media list that can be added to the archived list.
     * @return The amount of media objects that can be added to the archived list.
    */
    public int findArchivable() {
        int archCount = 0;

        for (Media m : media) {
            if ((m != null) && (m.isArchivable()) && !(this.archive.find((Archivable) m))) {
                System.out.println(m.toString());
                archCount++;
            }
        }

        return archCount;
    }

    /**
     * Archives the media objects that are within the media list.
     * @return The number of media objects that were archived.
    */
    public int archive() {
        int archCount = 0;

        for (Media m : media) {
            if ((m != null) && (m.isArchivable()) && !(this.archive.find((Archivable) m))) {
                this.archive.add((Archivable) m);
                archCount++;
            }
        }

        return archCount;
    }

    /**
     * Prints the archive list.
     * @return The total amount of archived media objects.
    */
    public int viewArchived() {
        int archCount = 0;

        for (Media m : media) {
            if (this.archive.find((Archivable) m)) {
                System.out.println(m.toString());
                archCount++;
            }
        }

        return archCount;
    }

    /**
     * Prints movies within the media list.
    */
    public void viewMovies() {
        System.out.println("Type\tTitle\t\t\t\t\t\t\t\tCategory\t\tYear\tSize(KB)\tDirector\tRevenue");
        int movieCount = 0;

        for (Media m : media) {
            if (m instanceof Movie) {
                System.out.println(m.toString());
                movieCount++;
            }
        }

        System.out.printf("There are %d movies in the media list\n", movieCount);
    }

    /**
     * Prints songs within the media list.
    */
    public void viewSongs() {
        System.out.println("Type\tTitle\t\t\t\t\t\t\t\tCategory\t\tYear\tSize(KB)\tArtist");
        int songCount = 0;

        for (Media s : media) {
            if (s instanceof Song) {
                System.out.println(s.toString());
                songCount++;
            }
        }

        System.out.printf("There are %d songs in the media list\n", songCount);
    }
}