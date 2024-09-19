/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A abstract class of that defines the object's form.
*/
public abstract class Shape implements Cloneable, Comparable<Shape>, Scalable {
    private String color;

    /**
     * Creates a shape. Default configuration.
    */
    protected Shape() {
        color = "None";
    }

    /**
     * Creates a shape with the specified color.
    */
    protected Shape(String color) {
        this.color = color;
    }

    /**
     * Gets the color of the shape.
     * @return The color of the shape.
    */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the shape.
     * @param color The color of the shape.
    */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns the shape's color.
    */
    public String toString() {
        return String.format("%-10s", color);
    }

    /**
     * Compares the area of two shapes.
    */
    public int compareTo(Shape shape) {
        if (this.getArea() > shape.getArea()) {
            return 1;
        } else if (this.getArea() < shape.getArea()) {
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