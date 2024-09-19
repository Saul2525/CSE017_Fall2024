/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A class that creates a rectangle shape.
*/
public class Rectangle extends Shape {
    private double length;
    private double width;

    /**
     * Creates a rectangle. Default configuration.
    */
    public Rectangle() {
        super();
        length = 0.0;
        width = 0.0;
    }

    /**
     * Creates a rectangle with the specified color, length, and width.
     * @param c The rectangle's color.
     * @param l The rectangle's length.
     * @param w The rectangle's width.
    */
    public Rectangle(String c, double l, double w) {
        super(c);
        this.length = l;
        this.width = w;
    }

    /**
     * Gets the length of the rectangle.
     * @return The rectangle's length.
    */
    public double getLength() {
        return length;
    }

    /**
     * Gets the width of the rectangle.
     * @return The rectangle's width.
    */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the length of the rectangle.
     * @param l The rectangle's length.
    */
    public void setLength(double l) {
        this.length = l;
    }

    /**
     * Sets the width of the rectangle.
     * @param w The rectangle's width.
    */
    public void setWidth(double w) {
        this.width = w;
    }

    /**
     * Returns the rectangle's color, length, width, area, and perimeter.
    */
    @Override public String toString() {
        return String.format("%-10s\t%s\t%-15.2f\t%-15.2f\t\t\t%-10.2f\t%-10.2f", "Rectangle", super.toString(), length, width, getArea(), getPerimeter());
    }

    /**
     * Gets the area of the rectangle.
    */
    public double getArea() {
        return (length * width);
    }

    /**
     * Gets the perimeter of the rectangle.
    */
    public double getPerimeter() {
        return (2 * (length + width));
    }

    /**
     * Clones the rectangle's color, length, and width.
    */
    public Object clone() {
        return new Rectangle(getColor(), length, width);
    }

    /**
     * Scales the rectangle's length and width with the inputted double.
    */
    public void scale(double f) {
        length *= f;
        width *= f;
    }
}