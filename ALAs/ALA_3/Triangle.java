/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A class that creates a triangle shape.
*/
public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    /**
     * Creates a triangle. Default configuration.
    */
    public Triangle() {
        super();
        side1 = 0.0f;
        side2 = 0.0f;
        side3 = 0.0f;
    }

    /**
     * Creates a triangle with the specified color and side lengths.
     * @param c The triangle's color.
     * @param s1 The length of the triangle's side.
     * @param s2 The length of the triangle's side.
     * @param s3 The length of the triangle's side.
    */
    public Triangle(String c, double s1, double s2, double s3) {
        super(c);
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
    }

    /**
     * Gets the length of a triangle's side.
     * @return The length of a triangle's side.
    */
    public double getSide1() {
        return side1;
    }

    /**
     * Gets the length of a triangle's side.
     * @return The length of a triangle's side.
    */
    public double getSide2() {
        return side2;
    }

    /**
     * Gets the length of a triangle's side.
     * @return The length of a triangle's side.
    */
    public double getSide3() {
        return side2;
    }

    /**
     * Sets the length of a triangle's side.
     * @param s1 The length of a triangle's side.
    */
    public void setSide1(double s1) {
        this.side1 = s1;
    }
    
    /**
     * Sets the length of a triangle's side.
     * @param s2 The length of a triangle's side.
    */
    public void setSide2(double s2) {
        this.side2 = s2;
    }

    /**
     * Sets the length of a triangle's side.
     * @param s3 The length of a triangle's side.
    */
    public void setSide3(double s3) {
        this.side3 = s3;
    }

    /**
     * Returns the triangle's color, length of each side, area, and perimeter.
    */
    @Override public String toString() {
        return String.format("%-10s\t%s\t%-10.2f\t%-10.2f\t%-10.2f\t%-10.2f\t%-10.2f", "Triangle", super.toString(), side1, side2, side3, getArea(), getPerimeter());
    }

    /**
     * Gets the area of the triangle.
    */
    public double getArea() {
        double s = (getPerimeter() / 2);
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /**
     * Gets the perimeter of the triangle.
    */
    public double getPerimeter() {
        return (side1 + side2 + side3);
    }

    /**
     * Clones the triangle's color and length of each side.
    */
    public Object clone() {
        return new Triangle(getColor(), side1, side2, side3);
    }

    /**
     * Scales each side of the triangle with the inputted double.
    */
    public void scale(double f) {
        side1 *= f;
        side2 *= f;
        side3 *= f;
    }
}