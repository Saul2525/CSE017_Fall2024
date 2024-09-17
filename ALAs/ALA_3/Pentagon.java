/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

public class Pentagon extends Shape {
    private double side;

    public Pentagon() {
        super();
        side = 0.0;
    }

    public Pentagon(String c, double s) {
        super(c);
        this.side = s;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double s) {
        this.side = s;
    }

    @Override public String toString() {
        return "";
    }

    public double getArea() {
        return (.25 * Math.sqrt(5 * (5 + (2 * Math.sqrt(5)))) * Math.pow(side, 2));
    }

    public double getPerimeter() {
        return (5.0 * side);
    }

    public Object clone() {
        return new Pentagon(getColor(), side);
    }

    public void scale(double f) {
        side *= f;
    }
}