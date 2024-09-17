/*
    Saul Toribio
    9/16/24
    CSE017 Fall 2024: PA 2
    IDE: VSCode; JDK: 11
*/

/**
 * A class representing a song within the class Media.
*/
public class Song extends Media {
    private String artist;

    /**
     * Creates a song. Default configuration.
    */
    public Song() {
        super();
        artist = "None";
    }

    /**
     * Creates a song with the specified title, category, year, size, and artist.
     * 
     * @param title The song's name.
     * @param category The song's genre.
     * @param year The song's year of release.
     * @param size The song's size in KB.
     * @param artist The artist's/band's name.
    */
    public Song(String title, String category, int year, long size, String artist) {
        super(title, category, year, size);
        this.artist = artist;
    }

    /**
     * Gets the name of the artist/band.
     * @return A string representing the name of the artist/band.
    */
    public String getArtist() {
        return artist;
    }

    /**
     * @return A formated String containing type, super.toString(), which contains title, category, year, and size, and artist.
    */
    @Override public String toString() {
        String type = "Song";
        return String.format("%-5s\t%s\t%-15s", type, super.toString(), artist);
    }

    /**
     * @return A formated String containing type, super.fileToString(), which contains title, category, year, and size, and artist.
    */
    @Override public String fileToString() {
        String type = "Song";
        return String.format("%s,%s,%s", type, super.fileToString(), artist);
    }

    /**
     * Sets the name of the artist/band.
     * @param artist A string representing the name of the artist/band.
    */
    public void setArtist(String artist) {
        this.artist = artist;
    }
}