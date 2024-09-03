/*
    Saul Toribio
    9/3/24
    CSE017 Fall 2024: PA 1
    IDE: VSCode; JDK: 11
*/

package PAs.PA_1;

public class Song extends Media {
    private String artist;

    public Song() {
        super();
        artist = "None";
    }

    public Song(String title, String category, int year, long size, String artist) {
        super(title, category, year, size);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public String toString() {
        String type = "Song";
        return String.format("%-5s\t%s\t%-15s", type, super.toString(), artist);
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}