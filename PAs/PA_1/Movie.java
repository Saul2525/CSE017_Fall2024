/*
    Saul Toribio
    9/3/24
    CSE017 Fall 2024: PA 1
    IDE: VSCode; JDK: 11
*/

/**
 * A class representing a movie within the class Media.
*/
public class Movie extends Media {
    private String director;
    private long revenue;

    /**
     * Creates a movie. Default configuration.
    */
    public Movie() {
        super();
        director = "None";
        revenue = 0;
    }

    /**
     * Creates a movie with the specified title, category, year, size, director, and revenue.
     * 
     * @param title The movie's name.
     * @param category The movie's genre.
     * @param year The movie's year of release.
     * @param size The movie's size in KB.
     * @param director The director's name.
     * @param revenue The amount of revenue the movie made.
    */
    public Movie(String title, String category, int year, long size, String director, long revenue) {
        super(title, category, year, size);
        this.director = director;
        this.revenue = revenue;
    }

    /**
     * Gets the name of the director.
     * @return A string representing the name of the director.
    */
    public String getDirector() {
        return director;
    }

    /**
     * Gets the revenue of the movie.
     * @return A long representing the revenue of the movie.
    */
    public long getRevenue() {
        return revenue;
    }

    /**
     * Formats the revenue of the movie into a more readable format.
     * @return A string representing the revenue of the movie.
    */
    private String formatMoney() {
        return java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("en", "US")).format(revenue);
    }

    /**
     * @return A formated String containing type, super.toString(), which contains title, category, year, and size, director, and a formated revenue.
    */
    @Override public String toString() {
        String type = "Movie";
        return String.format("%-5s\t%s\t%-15s\t%s", type, super.toString(), director, formatMoney());
    }

    /**
     * Sets the name of the director.
     * @param director A string representing the name of the director.
    */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets the revenue of the movie.
     * @param revenue A long representing the revenue of the movie.
    */
    public void getRevenue(long revenue) {
        this.revenue = revenue;
    }
}