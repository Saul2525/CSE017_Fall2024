/*
    Saul Toribio
    9/3/24
    CSE017 Fall 2024: PA 1
    IDE: VSCode; JDK: 11
*/

/**
 * A class storing a set of media objects from the class Media.
*/
public class MediaList {
    private Media[] media;
    private int count;

    /**
     * Creates a media list. Default configuration.
    */
    public MediaList() {
        media = new Media[10];
        count = 0;
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

    /**
     * Returns a media list matching the inputted year.
     * @param year The year to be matched with media objects of the same year within the media list.
     * @return A media list containing media objects with the matching year. Null otherwise.
    */
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

    /**
     * Removes a media object from the media list.
     * @param title The title to be matched with a media object within the media list.
     * @return True if the media object was removed from the media list. False otherwise.
    */
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

    /**
     * Sorts and prints the media list by name or by category.
     * @param sortType True to sort the media list by name. False to sort it by category.
    */
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
                media[j].toString();
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
                media[j].toString();
            }
        }
    }

    /**
     * Returns the amount of media objects within the media list.
     * @return What I said above. Or basically it just returns the parameter count.
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
}