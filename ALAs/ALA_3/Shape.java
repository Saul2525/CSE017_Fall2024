/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

public abstract class Shape implements Cloneable, Comparable<Shape>, Scalable {
    private String color;

    protected Shape() {
        color = "None";
    }

    protected Shape(String color) {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "";
    }

    public int compareTo(Shape otherShape) {
        double shapeArea = this.getArea();
        double otherShapeArea = otherShape.getArea();

        if (shapeArea > otherShapeArea) {
            return 1;
        } else if (shapeArea < otherShapeArea) {
            return -1;
        } else {
            return 0;
        }
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Object clone();

    public abstract void scale(double f);
}