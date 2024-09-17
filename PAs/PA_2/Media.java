/*
    Saul Toribio
    9/16/24
    CSE017 Fall 2024: PA 2
    IDE: VSCode; JDK: 11
*/

/**
 * A abstract class representing the tool used to deliver content.
*/
public abstract class Media {
    private String title, category;
    private int year;
    private long size;

    /**
     * Creates a media object. Default configuration.
    */
    protected Media() {
        this("None", "None", 0, 0);
    }

    /**
     * Creates a media object with the specified title, category, year, and size.
     * 
     * @param title The media object's name.
     * @param category The media object's genre.
     * @param year The media object's year of release.
     * @param size The media object's size in KB.
    */
    protected Media(String title, String category, int year, long size) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.size = size;
    }

    /**
     * Gets the name of the media object.
     * @return A string representing the name of the media object.
    */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the genre of the media object.
     * @return A string representing the genre of the media object.
    */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the year of the media object.
     * @return A int representing the year of the media object.
    */
    public int getYear() {
        return year;
    }

    /**
     * Gets the size of the media object.
     * @return A long representing the size of the media object in KB.
    */
    public long getSize() {
        return size;
    }

    /**
     * @return A formated String containing the title, category, year, and size.
    */
    public String toString() {
        return String.format("%-35s\t%-15s\t%-4d\t%-10d", title, category, year, size);
    }

    /**
     * @return A formated String containing the title, category, year, and size.
    */
    public String fileToString() {
        return String.format("%s,%s,%d,%d", title, category, year, size);
    }

    /**
     * Sets the name of the media object.
     * @param title A string representing the name of the media object.
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the genre of the media object.
     * @param category A string representing the genre of the media object.
    */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the year of the media object.
     * @param year A int representing the year of the media object.
    */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the size of the media object.
     * @param size A long representing the size of the media object in KB.
    */
    public void setSize(long size) {
        this.size = size;
    }
}