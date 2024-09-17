/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

public class TestShapes {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];

        shapes[0] = new Circle("Black", 2.5);
        shapes[1] = new Triangle("Green", 6.0, 6.0, 6.0);
        shapes[2] = new Rectangle("Red", 5.0, 3.0);
        shapes[3] = new Pentagon("Yellow", 7.0);
        shapes[4] = (Shape) shapes[0].clone();
        shapes[5] = (Shape) shapes[1].clone();
        shapes[6] = (Shape) shapes[2].clone();
        shapes[7] = (Shape) shapes[3].clone();

        shapes[4].scale(2.0);
        shapes[5].setColor("Orange");
    }
}