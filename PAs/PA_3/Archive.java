/*
    Saul Toribio
    9/23/24
    CSE017 Fall 2024: PA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A class storing a set of archived media objects from the class Media.
*/
public class Archive {
    private Archivable[] list;
    private int count;

    /**
     * Creates a archive. Default configuration.
    */
    public Archive() {
        list = new Archivable[10];
        count = 0;
    }

    /**
     * Adds a media object to the archive list.
     * @param a A archivable media object.
     * @return True if the media object was archived. False otherwise.
    */
    public boolean add(Archivable a) {
        if (count < 10) {
            list[count++] = a;
            return true;
        }

        return false;
    }

    /**
     * Finds a archived media object from the archive list.
     * @param a A archivable media object.
     * @return True if the archived media object was found. False otherwise.
    */
    public boolean find(Archivable a) {
        for (Archivable b : list) {
            if ((b != null) && (b.equals(a))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes a archived media object from the archive list.
     * @param a A archivable media object.
     * @return True if the archived media object was removed. False otherwise.
    */
    public boolean remove(Archivable a) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(a)) {
                list[i] = list[count - 1];
                list[count - 1] = null;
                count--;

                return true;
            }
        }

        return false;
    }

    /**
     * @return A formated String of an archived media object.
    */
    public String toString() {
        String returnString = "";

        for (Archivable a : list) {
            returnString += a.toString() + "\n";
        }

        return returnString;
    }

    /**
     * @return Returns the amount of archived media objects within the archive list.
    */
    public int size() {
        return count;
    }
}