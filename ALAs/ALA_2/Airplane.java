/*
    Saul Toribio
    9/5/24
    CSE017 Fall 2024: ALA 2
    IDE: VSCode; JDK: 11
*/

package ALAs.ALA_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Airplane {
    private char[][] seatMap;

    public Airplane() {
        seatMap = new char[9][8];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; i++) {
                seatMap[i][j] = '.';
            }
        }
    }

    public Airplane(String fileName) {
        seatMap = new char[9][8];
        readMap(fileName);
    }

    private void readMap(String fileName) {
        try {
            Scanner readFile = new Scanner(new File(fileName));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; i++) {
                    seatMap[i][j] = readFile.next().charAt(0);
                }
            }

            readFile.close();
        } catch(FileNotFoundException e) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; i++) {
                    seatMap[i][j] = '.';
                }
            }
        }
    }

    private boolean checkSeatNumber(String seatNumber) throws InvalidSeatException {
        if (seatNumber.matches("[1-9][A-H]")) {
            return true;
        } else {
            throw new InvalidSeatException(String.format("Invalid seat number: %s. Must be [1-9][A-H].", seatNumber));
        }
    }

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

    public String toString() {
        String out = "\tA\tB\tC\tD\tE\tF\tG\tH\n";

        for (int i = 0; i < 9; i++) {
            out += i++ + "\t";

            for (int j = 0; j < 8; j++) {
                out += seatMap[i][j] + "\t";
            }

            out += "\n";
        }

        return out;
    }
}