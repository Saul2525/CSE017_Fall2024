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

        System.out.printf("A new media list was created with %d media\n", count);
    }

    public boolean add(Media media) {
        if (count < 9) {
            this.media[count++] = media;

            System.out.printf("%d media added to the media list\n", count);
            return true;
        }

        System.out.println("The media list is full. Cannot add new media");
        return false;
    }

    public Media findTitle(String title) {
        for (int i = 0; i < count; i++) {
            if (media[i].getTitle().equals(title)) {
                System.out.printf("Media found with title \"%s\":\n", title);
                return media[i];
            }
        }

        System.out.printf("No media found with title \"%s\"\n", title);
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

            System.out.printf("%d media found with category \"%s\"\n", categoryCount, category);
            return tempMedia;
        }

        System.out.printf("No media found with category \"%s\"\n", category);
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

            System.out.printf("%d media found with year \"%s\"\n", yearCount, year);
            return tempMedia;
        }

        System.out.printf("No media found with year \"%d\"\n", year);
        return null;
    }

    public boolean remove(String title) {
        if (count > 0) {
            for (int k = 0; k < count; k++) {
                if (media[k].getTitle().equals(title)) {
                    System.out.printf("Media found with title \"%s\"", title);

                    for (int j = k; k < count - 1; j++) {
                        media[j] = media[j + 1];
                    }
                    media[count - 1] = null;
                    count--;

                    System.out.println("Media removed successfully");
                    return true;
                }
            }
        }

        System.out.printf("Media with title \"%s\" not found\n", title);
        return false;
    }

    public void sort(boolean sortType) {
        System.out.println("Type\tTitle\tCategory\tYear\tSize(KB)\tArtist/Director Revenue");

        if (sortType) {
            for (int i = 0; i < count; i++) {
                Media currentMedia = media[i];
                int j = i;

                while ((j > 0) && (currentMedia.getTitle().compareTo(media[j - 1].getTitle()) > 0)) {
                    media[j] = media[j - 1];
                    j--;
                }

                media[j] = currentMedia;
                media[j].toString();
            }
        } else {
            for (int i = 0; i < count; i++) {
                Media currentMedia = media[i];
                int j = i;

                while ((j > 0) && (currentMedia.getCategory().compareTo(media[j - 1].getCategory()) > 0)) {
                    media[j] = media[j - 1];
                    j--;
                }

                media[j] = currentMedia;
                media[j].toString();
            }
        }
    }

    public int size() {
        return count;
    }

    public String toString() {
        System.out.println("Type\tTitle\tCategory\tYear\tSize(KB)\tArtist/Director Revenue");
        String tempString = "";

        for (int i = 0; i < count; i++) {
            tempString += media[i].toString() + "";
        }

        return tempString;
    }
}