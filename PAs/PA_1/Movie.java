/*
    Saul Toribio
    9/3/24
    CSE017 Fall 2024: PA 1
    IDE: VSCode; JDK: 11
*/

public class Movie extends Media {
    private String director;
    private long revenue;

    public Movie() {
        super();
        director = "None";
        revenue = 0;
    }

    public Movie(String title, String category, int year, long size, String director, long revenue) {
        super(title, category, year, size);
        this.director = director;
        this.revenue = revenue;
    }

    public String getDirector() {
        return director;
    }

    public long getRevenue() {
        return revenue;
    }

    private String formatMoney() {
        return java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("en", "US")).format(revenue);
    }

    @Override public String toString() {
        String type = "Movie";
        return String.format("%-5s\t%s\t%-15s\t%s", type, super.toString(), director, formatMoney());
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void getRevenue(long revenue) {
        this.revenue = revenue;
    }
}