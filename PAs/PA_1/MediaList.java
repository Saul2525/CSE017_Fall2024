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
        if (count < 10) {
            this.media[count++] = media;
            return true;
        }

        return false;
    }

    public Media findTitle(String title) {
        for (int i = 0; i < count; i++) {
            if (media[i].getTitle().equals(title)) {
                return media[i];
            }
        }

        return null;
    }

    public Media[] findCategory(String category) {
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

    public Media[] findYear(int year) {
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

    public boolean remove(String title) {
        if (count > 0) {
            for (int k = 0; k < count; k++) {
                if (media[k].getTitle().equals(title)) {

                    for (int j = k; j < count - 1; j++) {
                        media[j] = media[j + 1];
                    }
                    media[count - 1] = null;
                    count--;

                    return true;
                }
            }
        }

        return false;
    }

    public void sort(boolean sortType) {
        if (sortType) {
            for (int i = 0; i < count; i++) {
                Media currentMedia = media[i];
                int j = i;

                while ((j > 0) && (currentMedia.getTitle().compareTo(media[j - 1].getTitle()) < 0)) {
                    media[j] = media[j - 1];
                    j--;
                }

                media[j] = currentMedia;
            }
        } else {
            for (int i = 0; i < count; i++) {
                Media currentMedia = media[i];
                int j = i;

                while ((j > 0) && (currentMedia.getCategory().compareTo(media[j - 1].getCategory()) < 0)) {
                    media[j] = media[j - 1];
                    j--;
                }

                media[j] = currentMedia;
            }
        }
    }

    public int size() {
        return count;
    }

    public String toString() {
        System.out.println("Type\tTitle\t\t\t\t\t\t\t\tCategory\t\tYear\tSize(KB)\tArtist/Director\tRevenue");
        String tempString = "";

        for (int i = 0; i < count; i++) {
            tempString += media[i].toString() + "\n";
        }

        return tempString;
    }
}