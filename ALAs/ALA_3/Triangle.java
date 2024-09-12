/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle() {
        super();
        side1 = 0.0f;
        side2 = 0.0f;
        side3 = 0.0f;
    }

    public Triangle(String c, double s1, double s2, double s3) {
        super(c);
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side2;
    }

    public void setSide1(double s1) {
        this.side1 = s1;
    }
    
    public void setSide2(double s2) {
        this.side2 = s2;
    }

    public void setSide3(double s3) {
        this.side3 = s3;
    }

    @Override public String toString() {
        return "";
    }

    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return (side1 + side2 + side3);
    }

    public Object clone() {
        return new Triangle(getColor(), side1, side2, side3);
    }

    public void scale(double f) {
        side1 *= f;
        side2 *= f;
        side3 *= f;
    }
}