/*
    Saul Toribio
    9/12/24
    CSE017 Fall 2024: ALA 3
    IDE: VSCode; JDK: 11
*/

/**
 * A class that used to test Shape, Circle, Triangle, Rectangle, Pentagon, and Scalable.
*/
public class TestShapes {
    /**
     * Prints the lists of shapes.
     * @param shapes The list of shapes.
    */
    public static void printShapes(Shape[] shapes) {
        System.out.printf("Shape\t\tColor\t\tDimensions\t\t\t\t\tArea\t\tPerimeter\n");

        for (Shape s : shapes) {
            System.out.println(s);
        }

        System.out.println();
    }

    /**
     * Returns the average perimeter of each shape currently in shape list.
     * @param shapes The list of shapes.
     * @return The average perimeter of the shape list.
    */
    public static double getAveragePerimeter(Shape[] shapes) {
        double totalPerimeter = 0.0;

        for (Shape s : shapes) {
            totalPerimeter += s.getPerimeter();
        }

        return (totalPerimeter / shapes.length);
    }

    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];

        shapes[0] = new Circle("Black", 2.5);
        shapes[1] = new Triangle("Green", 6.0, 6.0, 6.0);
        //shapes[2] = new Rectangle("Red", 5.0, 3.0);
        //shapes[3] = new Pentagon("Yellow", 7.0);

        shapes[4] = ((Shape) shapes[0].clone());
        shapes[5] = ((Shape) shapes[1].clone());
        shapes[6] = ((Shape) shapes[2].clone());
        shapes[7] = ((Shape) shapes[3].clone());

        ((Circle) shapes[4]).scale(2.0);
        ((Triangle) shapes[5]).setColor("Orange");
        //((Rectangle) shapes[6]).setLength(10.0);
        //((Pentagon) shapes[7]).setSide(4.0);

        System.out.println("Before sorting");
        printShapes(shapes);

        java.util.Arrays.sort(shapes);
        System.out.println("After sorting");
        printShapes(shapes);

        System.out.printf("Average Perimeter = %.2f\n", getAveragePerimeter(shapes));
    }
}