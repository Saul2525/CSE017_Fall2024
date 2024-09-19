/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A class that creates a circle shape.
*/
public class Circle extends Shape {
    private double radius;

    /**
     * Creates a circle. Default configuration.
    */
    public Circle() {
        super();
        radius = 0.0f;
    }
    
    /**
     * Creates a circle with the specified color and radius.
     * @param c The circle's color.
     * @param r The circle's radius.
    */
    public Circle(String c, double r) {
        super(c);
        this.radius = r;
    }

    /**
     * Gets the radius of the circle.
     * @return The radius of the circle.
    */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the circle.
     * @param r The radius of the circle.
    */
    public void setRadius(double r) {
        this.radius = r;
    }

    /**
     * Returns the circle's color, radius, area, and perimeter.
    */
    @Override public String toString() {
        return String.format("%-10s\t%s\t%-40.2f\t%-10.2f\t%-10.2f", "Circle", super.toString(), radius, getArea(), getPerimeter());
    }

    /**
     * Gets the area of the circle.
    */
    public double getArea() {
        return (Math.PI * Math.pow(radius, 2.0));
    }

    /**
     * Gets the perimeter of the circle.
    */
    public double getPerimeter() {
        return (2 * Math.PI * radius);
    }

    /**
     * Clones the circle's color and radius.
    */
    public Object clone() {
        return new Circle(getColor(), radius);
    }

    /**
     * Scales the circle's radius with the inputted double.
    */
    public void scale(double f) {
        radius *= f;
    }
}