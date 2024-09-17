/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

public class Circle extends Shape {
    private double radius;

    public Circle() {
        super();
        radius = 0.0f;
    }

    public Circle(String c, double r) {
        super(c);
        this.radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double r) {
        this.radius = r;
    }

    @Override public String toString() {
        return String.format("%-10s\t%s\t%-30.2f\t%-10.2f\t%-10.2f", "Circle", super.toString(), radius, getArea(), getPerimeter());
    }

    public double getArea() {
        return (Math.PI * Math.pow(radius, 2.0));
    }

    public double getPerimeter() {
        return (2 * Math.PI * radius);
    }

    public Object clone() {
        return new Circle(getColor(), radius);
    }

    public void scale(double f) {
        radius *= f;
    }
}