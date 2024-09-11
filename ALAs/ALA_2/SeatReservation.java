/*
    Saul Toribio
    9/5/24
    CSE017 Fall 2024: ALA 2
    IDE: VSCode; JDK: 11
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class SeatReservation {
    public static void main(String[] args) {
        Airplane airplane = new Airplane("seatmap.txt");
        System.out.println(airplane.toString());
        System.out.println("Welcome, user. Please make a selection.\n1: Reserve a Seat\n2: Free a Seat\n3: Quit Program");

        int selection = 0;
        String seatNumber = "";
        boolean programMenu = true;
        while (programMenu == true) {
            Scanner scnr = new Scanner(System.in);
            selection = scnr.nextInt();

            try {
                switch (selection) {
                    case 1:
                        System.out.println("Enter a seat number: ");
                        seatNumber = scnr.next();

                        if (airplane.reserveSeat(seatNumber)) {
                            System.out.printf("%s successfully reserved.\n", seatNumber);
                        } else {
                            System.out.printf("%s already reserved.\n", seatNumber);
                        }
                        break;
                    case 2:
                        System.out.println("Enter a seat number: ");
                        seatNumber = scnr.next();

                        if (airplane.freeSeat(seatNumber)) {
                            System.out.printf("%s successfully freed.\n", seatNumber);
                        } else {
                            System.out.printf("%s already freed.\n", seatNumber);
                        }
                        break;
                    case 3:
                        airplane.saveMap("seatmap.txt");

                        programMenu = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please enter a number from 1-3.");
                        scnr.next();
                }
            } catch(InvalidSeatException e) {
                System.out.println(e.getMessage());
            } catch(InputMismatchException e) {
                System.out.println("Invalid input.");
                scnr.next();
            }
            
            scnr.close();
        }
    }
}