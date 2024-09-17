/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle() {
        super();
        length = 0.0;
        width = 0.0;
    }

    public Rectangle(String c, double l, double w) {
        super(c);
        this.length = l;
        this.width = w;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setLength(double l) {
        this.length = l;
    }

    public void setWidth(double w) {
        this.width = w;
    }

    @Override public String toString() {
        return "";
    }

    public double getArea() {
        return (length * width);
    }

    public double getPerimeter() {
        return (2 * (length + width));
    }

    public Object clone() {
        return new Rectangle(getColor(), length, width);
    }

    public void scale(double f) {
        length *= f;
        width *= f;
    }
}