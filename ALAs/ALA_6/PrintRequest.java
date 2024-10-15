/*
    Saul Toribio
    10/10/24
    CSE017 Fall 2024: ALA 6
    IDE: VSCode; JDK: 11
*/

public class PrintRequest implements Comparable<PrintRequest> {
    private String group;
    private long size;
    private int id;

    public PrintRequest(String group, long size, int id) {
        this.group = group;
        this.size = size;
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String toString() {
        return String.format("%-10d\t%-5s\t%-10d", id, group, size);
    }

    private int getIndex() {
        String[] groups = {"root", "admin", "user", "batch"};
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].equals(this.group)) {
                return i;
            }
        }

        return -1;
    }

    public int compareTo(PrintRequest pr) {
        int g1 = this.getIndex();
        int g2 = pr.getIndex();

        return g1 - g2;
    }
}