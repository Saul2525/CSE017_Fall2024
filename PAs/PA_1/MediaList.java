/*
    Saul Toribio
    9/3/24
    CSE017 Fall 2024: PA 1
    IDE: VSCode; JDK: 11
*/

public class MediaList {
    private Media[] media;
    private int count;

    public MediaList() {
        media = new Media[10];
        count = 0;
    }

    public boolean add(Media media) {
        if (count < 9) {
            this.media[count] = media;
            count++;

            return true;
        }

        return false;
    }

    public Media findTitle(String title) {
        return null;
    }

    public Media findCategory(String category) {
        return null;
    }

    public Media findYear(int year) {
        return null;
    }
}