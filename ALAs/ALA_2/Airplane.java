/*
    Saul Toribio
    9/5/24
    CSE017 Fall 2024: ALA 2
    IDE: VSCode; JDK: 11
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class that deals with reserving, freeing, and printing an map of an airplane.
*/
public class Airplane {
    private char[][] seatMap;

    /**
     * Creates a airplane. Default configuration.
    */
    public Airplane() {
        seatMap = new char[9][8];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                seatMap[i][j] = '.';
            }
        }
    }

    /**
     * Creates a airplane. Reads a txt file to create the map.
     * @param fileName The txt file's name.
    */
    public Airplane(String fileName) {
        seatMap = new char[9][8];
        readMap(fileName);
    }

    /**
     * Reads a txt to create the map of the airplane. Otherwise, creates a blank map.
     * @param fileName The txt file's name.
    */
    private void readMap(String fileName) {
        try {
            Scanner readFile = new Scanner(new File(fileName));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; j++) {
                    seatMap[i][j] = readFile.next().charAt(0);
                }
            }

            readFile.close();
        } catch(FileNotFoundException e) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; j++) {
                    seatMap[i][j] = '.';
                }
            }
        }
    }

    /**
     * Checks if the user's input is a valid seat number.
     * @param seatNumber The user's inputted seat number.
     * @return True if the user's inputted seat number is valid. False otherwise.
     * @throws InvalidSeatException A String stating the user's inputted seat number is invalid.
    */
    private boolean checkSeatNumber(String seatNumber) throws InvalidSeatException {
        if (seatNumber.matches("[1-9][A-H]")) {
            return true;
        } else {
            throw new InvalidSeatException(String.format("Invalid seat number: %s. Must be [1-9][A-H].\n", seatNumber));
        }
    }

    /**
     * Reserves a seat for the user on the airplane.
     * @param seatNumber The user's inputted seat number.
     * @return True if the reservation of the user's choice was successful. False otherwise.
     * @throws InvalidSeatException If the user's inputted seat number was invalid.
    */
    public boolean reserveSeat(String seatNumber) throws InvalidSeatException {
        if (checkSeatNumber(seatNumber)) {
            int row = seatNumber.charAt(0) - '1';
            int col = seatNumber.charAt(1) - 'A';

            if (seatMap[row][col] == '.') {
                seatMap[row][col] = 'X';
                return true;
            }
        }

        return false;
    }

    /**
     * Frees up a seat for the user on the airplane.
     * @param seatNumber The user's inputted seat number.
     * @return True if the seat of the user was freed up. False otherwise.
     * @throws InvalidSeatException If the user's inputted seat number was invalid.
    */
    public boolean freeSeat(String seatNumber) throws InvalidSeatException {
        if (checkSeatNumber(seatNumber)) {
            int row = seatNumber.charAt(0) - '1';
            int col = seatNumber.charAt(1) - 'A';

            if (seatMap[row][col] == 'X') {
                seatMap[row][col] = '.';
                return true;
            }
        }

        return false;
    }

    /**
     * Saves the current state of the airplane.
     * @param fileName The txt file's name.
    */
    public void saveMap(String fileName) {
        try {
            PrintWriter writerFile = new PrintWriter(new File(fileName));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; j++) {
                    writerFile.print(seatMap[i][j] + " ");
                }
            }

            writerFile.close();
        } catch(FileNotFoundException e) {
            System.out.println("Cannot write to file.");
        }
    }

    /**
     * Prints out the current state of the plane.
    */
    public String toString() {
        String out = "\tA\tB\tC\tD\tE\tF\tG\tH\n";

        for (int i = 0; i < 9; i++) {
            out += (i + 1) + "\t";

            for (int j = 0; j < 8; j++) {
                out += seatMap[i][j] + "\t";
            }

            out += "\n";
        }

        return out;
    }
}