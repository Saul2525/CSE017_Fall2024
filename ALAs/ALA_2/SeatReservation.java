/*
    Saul Toribio
    9/5/24
    CSE017 Fall 2024: ALA 2
    IDE: VSCode; JDK: 11
*/

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that deals with reservations on an airplane.
*/
public class SeatReservation {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Airplane airplane = new Airplane("seatmap.txt");

        int selection = 0;
        String seatNumber = "";
        boolean programMenu = true;
        do {
            System.out.println(airplane.toString());
            System.out.println("Welcome, user. Please make a selection.\n1: Reserve a Seat\n2: Free a Seat\n3: Quit");

            try {
                selection = scnr.nextInt();

                switch (selection) {
                    case 1:
                        System.out.print("\nEnter a seat number: ");
                        seatNumber = scnr.next();

                        if (airplane.reserveSeat(seatNumber)) {
                            System.out.printf("%s successfully reserved.\n", seatNumber);
                        } else {
                            System.out.printf("%s already reserved.\n", seatNumber);
                        }

                        System.out.println();
                        break;
                    case 2:
                        System.out.print("\nEnter a seat number: ");
                        seatNumber = scnr.next();

                        if (airplane.freeSeat(seatNumber)) {
                            System.out.printf("%s successfully freed.\n", seatNumber);
                        } else {
                            System.out.printf("%s already freed.\n", seatNumber);
                        }

                        System.out.println();
                        break;
                    case 3:
                        System.out.println("\nThank you for using my airplane seat reservation program.");
                        airplane.saveMap("seatmap.txt");

                        programMenu = false;
                        break;
                    default:
                        System.out.println("\nInvalid selection. Please enter a number from 1-3.\n");
                        break;
                }
            } catch(InvalidSeatException e) {
                System.out.println(e.getMessage());
            } catch(InputMismatchException e) {
                System.out.println("\nInvalid input.");
                scnr.next();
            }
        } while (programMenu == true);

        scnr.close();
    }
}