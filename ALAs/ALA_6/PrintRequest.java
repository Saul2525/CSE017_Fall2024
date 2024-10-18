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

    public PrintRequest() {
        group = "";
        size = 0;
        id = 0;
    }

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