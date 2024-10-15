/*
    Saul Toribio
    10/14/24
    CSE017 Fall 2024: PA 5
    IDE: VSCode; JDK: 11
*/

/**
 * A class that extends the abstract class Number and creates a rational fractions. Math methods included.
*/
public class Rational extends Number {
    private int numerator;
    private int denominator;

    /**
     * Creates a rational. Default configuration.
    */
    public Rational() {
        numerator = 1;
        denominator = 1;
    }

    /**
     * Creates a rational with a inputted numerator and denominator and reduces it.
     * @param n The numerator.
     * @param d The denominator.
    */
    public Rational(int n, int d) {
        numerator = n;
        denominator = d;
        reduce();
    }

    /**
     * Gets the numerator.
     * @return The numerator.
    */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets the denominator.
     * @return The denominator.
    */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Sets the numerator.
     * @param n The numerator.
    */
    public void setNumerator(int n) {
        numerator = n;
    }

    /**
     * Sets the denominator.
     * @param d The denominator.
    */
    public void setDenominator(int d) {
        denominator = d;
    }

    /**
     * Prints the rational based on certain conditions.
    */
    public String toString() {
        if (numerator == 0) {
            return String.format("0");
        } else if (numerator == denominator) {
            return String.format("1");
        } else if (denominator == 1) {
            return String.format("%d", numerator);
        } else if (denominator < 0) {
            return String.format("-%d/%d", numerator, denominator);
        }
        return String.format("%d/%d", numerator, denominator);
    }

    /**
     * Adds two rationals.
     * @param r A rational.
     * @return The added rational.
    */
    public Rational add(Rational r) {
        return new Rational(((numerator * r.getDenominator()) + (r.getNumerator() * denominator)), (denominator * r.getDenominator()));
    }

    /**
     * Subtracts two rationals.
     * @param r A rational.
     * @return The subtracted rational.
    */
    public Rational sub(Rational r) {
        return new Rational(((numerator * r.getDenominator()) - (r.getNumerator() * denominator)), (denominator * r.getDenominator()));
    }

    /**
     * Multiplies two rationals.
     * @param r A rational.
     * @return The multiplied rational.
    */
    public Rational mult(Rational r) {
        return new Rational((numerator * r.getNumerator()), (denominator * r.getDenominator()));
    }

    /**
     * Divides two rationals.
     * @param r A rational.
     * @return The divided rational.
    */
    public Rational div(Rational r) {
        return new Rational((numerator * r.getDenominator()), (denominator * r.getNumerator()));
    }

    /**
     * Finds the GCD of the numerator and the denominator.
     * @param numerator What it says.
     * @param denominator What it says.
     * @return The GCD of the numerator and the denominator.
    */
    private int gcd(int numerator, int denominator) {
        if ((numerator % denominator) == 0) {
            return denominator;
        } else {
            return gcd(denominator, (numerator % denominator));
        }
    }

    /**
     * Reduces the rational into its simplest form.
    */
    private void reduce() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    /**
     * Returns the value of the numerator divided by the denominator as a int.
    */
    public int intValue() {
        return (numerator / denominator);
    }

    /**
     * Returns the value of the numerator divided by the denominator as a long.
    */
    public long longValue() {
        return (long)(intValue());
    }

    /**
     * Returns the value of the numerator divided by the denominator as a float.
    */
    public float floatValue() {
        return (float)(intValue());
    }

    /**
     * Returns the value of the numerator divided by the denominator as a double.
    */
    public double doubleValue() {
        return (double)(intValue());
    }

    /**
     * Checks if a rational equals another rational.
    */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Rational obj = (Rational) o;
        return ((numerator == obj.getNumerator()) && (denominator == obj.getDenominator()));
    }
}