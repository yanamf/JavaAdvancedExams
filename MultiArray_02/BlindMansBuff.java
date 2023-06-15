package MultiArray_02;

import java.util.Scanner;

public class BlindMansBuff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split("\\s+");
        int matrixRow = Integer.parseInt(dimensions[0]);
        int matrixCol = Integer.parseInt(dimensions[1]);

        String[][] matrix = new String[matrixRow][matrixCol];
        fillMatrix(matrix, scanner);

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("B")) {
                    currentRow = row;
                    currentCol = col;
                    matrix[currentRow][currentCol] = "-";
                }
            }
        }
        int movesMadeCount = 0;
        int touchedOpponentsCount = 0;
        String currentPosition;
        String command = scanner.nextLine();
        while (!command.equals("Finish") && touchedOpponentsCount < 3) {

            int temporaryRow = currentRow;
            int temporaryCol = currentCol;

            if (command.equals("up")) {
                temporaryRow = currentRow - 1;
            } else if (command.equals("down")) {
                temporaryRow = currentRow + 1;
            } else if (command.equals("left")) {
                temporaryCol = currentCol - 1;
            } else if (command.equals("right")) {
                temporaryCol = currentCol + 1;
            }
            if (isValidPosition(matrix, temporaryRow, temporaryCol)) {
                if (!matrix[temporaryRow][temporaryCol].equals("O")) {
                    currentRow = temporaryRow;
                    currentCol = temporaryCol;
                    currentPosition = matrix[currentRow][currentCol];
                    if (currentPosition.equals("P")) {
                        touchedOpponentsCount++;
                        movesMadeCount++;
                    } else if (currentPosition.equals("-")) {
                        movesMadeCount++;
                    }
                }
            }
            command = scanner.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d Moves made: %d", touchedOpponentsCount, movesMadeCount);

    }

    public static boolean isValidPosition(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split(" ");
            matrix[i] = currentRow;
        }
    }
}
