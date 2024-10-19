/*
    Saul Toribio
    10/10/24
    CSE017 Fall 2024: ALA 6
    IDE: VSCode; JDK: 11
*/

/**
 * A class that details what print request is. 
*/
public class PrintRequest implements Comparable<PrintRequest> {
    private String group;
    private long size;
    private int id;

    /**
     * Creates a print request. Default configuration.
    */
    public PrintRequest() {
        group = "";
        size = 0;
        id = 0;
    }

    /**
     * Creates a print request with specified group, size, and ID.
    */
    public PrintRequest(String group, long size, int id) {
        this.group = group;
        this.size = size;
        this.id = id;
    }

    /**
     * Returns group.
     * @return The group.
    */
    public String getGroup() {
        return group;
    }

    /**
     * Sets group.
     * @param group The group.
    */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Returns size.
     * @return The size.
    */
    public long getSize() {
        return size;
    }

    /**
     * Sets size.
     * @param size The size.
    */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * Returns ID.
     * @return The ID.
    */
    public int getID() {
        return id;
    }

    /**
     * Sets ID.
     * @param id The ID.
    */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Prints the ID, group, and formatted size of the print request.
    */
    public String toString() {
        String out = "";
        if (size > 1000000000) {
            size /= 1000000000.0;
            out = size + "GB";
        } else if (size > 1000000) {
            size /= 1000000.0;
            out = size + "MB";
        } else if (size > 1000) {
            size /= 1000.0;
            out = size + "KB";
        } else {
            out = size + "B";
        }

        return String.format("%-10d\t%-10s\t%-10s", id, group, out);
    }

    /**
     * Returns the index of the group.
     * @return The index of the group.
    */
    private int getIndex() {
        String[] groups = {"root", "admin", "user", "batch"};
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].equals(this.group)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Compares two print request's index.
    */
    public int compareTo(PrintRequest pr) {
        int g1 = this.getIndex();
        int g2 = pr.getIndex();

        return g1 - g2;
    }

    /**
     * Return the index of the priority.
     * @return The index of the priority.
    */
    public int getPriority() {
        String[] priorities = {"root", "admin", "user", "batch"};
        for (int i = 0; i < priorities.length; i++) {
            if (group.equals(priorities[i])) {
                return i;
            }
        }

        return -1;
    }
}