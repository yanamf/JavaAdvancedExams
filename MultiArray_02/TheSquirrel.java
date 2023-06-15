package MultiArray_02;

import java.util.Scanner;

public class TheSquirrel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        String[] directions = scanner.nextLine().split(",\\s+");
        String[][] matrix = new String[size][size];
        fillMatrix(matrix, scanner);

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("s")) {
                    currentRow = row;
                    currentCol = col;
                    matrix[currentRow][currentCol] = "*";
                }
            }
        }
        boolean isOutOfField = false;
        boolean is3Hazelnuts = false;
        boolean steppedOnTrap = false;

        String currentPosition = "";
        int hazelnutsCount = 0;
        for (int i = 0; i < directions.length; i++) {
            String currentDirection = directions[i];
            int temporaryRow = currentRow;
            int temporaryCol = currentCol;
            if (currentDirection.equals("up")) {
                temporaryRow = currentRow - 1;
            } else if (currentDirection.equals("down")) {
                temporaryRow = currentRow + 1;
            } else if (currentDirection.equals("left")) {
                temporaryCol = currentCol - 1;
            } else if (currentDirection.equals("right")) {
                temporaryCol = currentCol + 1;
            }
            if (isValidPosition(matrix, temporaryRow, temporaryCol)) {
                currentRow = temporaryRow;
                currentCol = temporaryCol;
                currentPosition = matrix[currentRow][currentCol];
                if (currentPosition.equals("h")) {
                    hazelnutsCount++;
                    matrix[currentRow][currentCol] = "*";
                    if (hazelnutsCount == 3) {
                        currentPosition = "s";
                        matrix[currentRow][currentCol] = currentPosition;
                        is3Hazelnuts = true;
                        break;
                    }
                } else if (currentPosition.equals("t")) {
                    steppedOnTrap = true;
                    break;
                }
            } else {
                isOutOfField = true;
                break;
            }
        }

        if (is3Hazelnuts) {
            System.out.println("Good job! You have collected all hazelnuts!");
        } else if (steppedOnTrap) {
            System.out.println("Unfortunately, the squirrel stepped on a trap...");
        } else if (isOutOfField) {
            System.out.println("The squirrel is out of the field.");
        } else if (hazelnutsCount < 3) {
            System.out.println("There are more hazelnuts to collect.");
        }
        System.out.printf("Hazelnuts collected: %d", hazelnutsCount);
    }

    public static boolean isValidPosition(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split("");
            matrix[i] = currentRow;
        }
    }
}
