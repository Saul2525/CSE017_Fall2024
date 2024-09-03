/*
    Saul Toribio
    9/3/24
    CSE017 Fall 2024: PA 1
    IDE: VSCode; JDK: 11
*/

package PAs.PA_1;

public abstract class Media {
    private String title, category;
    private int year;
    private long size;

    protected Media() {
        this("None", "None", 0, 0);
    }

    protected Media(String title, String category, int year, long size) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getYear() {
        return year;
    }

    public long getSize() {
        return size;
    }

    public String toString() {
        return String.format("%-35s\t%-15s\t%-4d\t%-10d", title, category, year, size);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSize(long size) {
        this.size = size;
    }
}