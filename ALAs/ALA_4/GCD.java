/*
    Saul Toribio
    9/19/24
    CSE017 Fall 2024: ALA 4
    IDE: VSCode; JDK: 11
*/

public class GCD {
    public static int iter1, iter2, iter3, iter4;

    public static int gcd_1(int m, int n) {
        int divisor = 1;
        iter1 = 0;

        for (int i = 2; ((i < m) && (i < n)); i++) {
            iter1++;

            if (((m % i) == 0) && ((n % i) == 0)) {
                divisor = i;
            }
        }

        return divisor;
    }

    public static int gcd_2(int m, int n) {
        int divisor = 1;
        iter2 = 0;

        for (int i = n; i >= 1; i--) {
            iter2++;

            if (((m % i) == 0) && ((n % i) == 0)) {
                divisor = i;
                break;
            }
        }

        return divisor;
    }

    public static int gcd_3(int m, int n) {
        int divisor = 1;
        iter3 = 0;

        if ((m % n) == 0) {
            return n;
        }

        for (int i = (n / 2); i >= 1; i--) {
            iter3++;

            if (((m % i) == 0) && ((n % i) == 0)) {
                divisor = i;
                break;
            }
        }

        return divisor;
    }

    public static int gcd_4(int m, int n) {
        iter4++;

        if ((m % n) == 0) {
            return n;
        } else {
            return gcd_4(n, (m % n));
        }
    }

    public static void compareExecutionTime() {
        for (int i = 0; i < 20; i++) {
            int num1 = (int)(Math.random() * 1000000);
            int num2 = (int)(Math.random() * 1000000);

            if (num2 > num1) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }

            long start = System.nanoTime();
            gcd_1(num1, num2);
            long end = System.nanoTime();
            long execTime1 = end - start;

            start = System.nanoTime();
            gcd_2(num1, num2);
            end = System.nanoTime();
            long execTime2 = end - start;

            start = System.nanoTime();
            gcd_3(num1, num2);
            end = System.nanoTime();
            long execTime3 = end - start;

            start = System.nanoTime();
            gcd_4(num1, num2);
            end = System.nanoTime();
            long execTime4 = end - start;
            
            System.out.printf("%-10d %-10d %-10d %-10d %-10d %-10d\n", num1, num2, execTime1, execTime2, execTime3, execTime4);
        }
    }

    public static void compareIterations() {
        for (int i = 0; i < 20; i++) {
            int num1 = (int)(Math.random() * 1000000);
            int num2 = (int)(Math.random() * 1000000);

            if (num2 > num1) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }

            gcd_1(num1, num2);
            gcd_2(num1, num2);
            gcd_3(num1, num2);

            iter4 = 0;
            gcd_4(num1, num2);

            System.out.printf("%-10d %-10d %-10d %-10d %-10d %-10d\n", num1, num2, iter1, iter2, iter3, iter4);
        }
    }

    public static void main(String[] args) {
        System.out.println("Comparing the gcd methods using the execution time");
        System.out.println("Number1\t   Number2    gcd_1\t gcd_2\t    gcd_3      gcd_4");
        compareExecutionTime();

        System.out.println("\nComparing the gcd methods using the number of iterations");
        System.out.println("Number1\t   Number2    gcd_1\t gcd_2\t    gcd_3      gcd_4");
        compareIterations();
    }
}