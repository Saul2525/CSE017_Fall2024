/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A class that creates a pentagon shape.
*/
public class Pentagon extends Shape {
    private double side;

    /**
     * Creates a pentagon. Default configuration.
    */
    public Pentagon() {
        super();
        side = 0.0;
    }

    /**
     * Creates a pentagon with the specified color and sides.
     * @param c The color of the pentagon.
     * @param s The length of the pentagon's sides.
    */
    public Pentagon(String c, double s) {
        super(c);
        this.side = s;
    }

    /**
     * Gets the length of pentagon's sides.
     * @return The length of pentagon's sides.
    */
    public double getSide() {
        return side;
    }

    /**
     * Sets the length of pentagon's sides.
     * @param s The length of pentagon's sides.
    */
    public void setSide(double s) {
        this.side = s;
    }
    
    /**
     * Returns the pentagon's color, sides, area, and perimeter.
    */
    @Override public String toString() {
        return String.format("%-10s\t%s\t%-40.2f\t%-10.2f\t%-10.2f", "Pentagon", super.toString(), side, getArea(), getPerimeter());
    }

    /**
     * Gets the area of the pentagon.
    */
    public double getArea() {
        return (.25 * Math.sqrt(5 * (5 + (2 * Math.sqrt(5)))) * Math.pow(side, 2));
    }

    /**
     * Gets the perimeter of the pentagon.
    */
    public double getPerimeter() {
        return (5.0 * side);
    }
    
    /**
     * Clones the pentagon's color and sides.
    */
    public Object clone() {
        return new Pentagon(getColor(), side);
    }

    /**
     * Scales the pentagon's sides with the inputted double.
    */
    public void scale(double f) {
        side *= f;
    }
}