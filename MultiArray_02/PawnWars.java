package MultiArray_02;

import java.util.Scanner;

public class PawnWars {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sizeMatrix = 8;
        char letter = 'a';
        int number = 8;

        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrixChess(matrix, letter, number);

        String[][] newMatrix = new String[8][8];
        fillMatrix(newMatrix, scan);

        int whiteCurrentRow = findInitialPosition(newMatrix, "w")[0];
        int whiteCurrentCol = findInitialPosition(newMatrix, "w")[1];
        int blackCurrentRow = findInitialPosition(newMatrix, "b")[0];
        int blackCurrentCol = findInitialPosition(newMatrix, "b")[1];

        int cycle = -1;
        while (true) {
            cycle++;
            if (cycle % 2 == 0) {
                //white
                if ((whiteCurrentRow - 1) == 0) {
                    // has reached the end
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "-";
                    whiteCurrentRow--;
                    System.out.printf("Game over! White pawn is promoted to a queen at %s.\n", matrix[whiteCurrentRow][whiteCurrentCol]);
                    return;
                }
                if (isInBounds(newMatrix, whiteCurrentRow - 1, whiteCurrentCol - 1) && newMatrix[whiteCurrentRow - 1][whiteCurrentCol - 1].equals("b")) {
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "-";
                    whiteCurrentRow--;
                    whiteCurrentCol--;
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "w";
                    System.out.printf("Game over! White capture on %s.\n", matrix[whiteCurrentRow][whiteCurrentCol]);
                    return;
                } else if (isInBounds(newMatrix, whiteCurrentRow - 1, whiteCurrentCol + 1) && newMatrix[whiteCurrentRow - 1][whiteCurrentCol + 1].equals("b")) {
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "-";
                    whiteCurrentRow--;
                    whiteCurrentCol++;
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "w";
                    System.out.printf("Game over! White capture on %s.\n", matrix[whiteCurrentRow][whiteCurrentCol]);
                    return;
                }
                if (isInBounds(matrix, whiteCurrentRow - 1, whiteCurrentCol)) {
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "-";
                    whiteCurrentRow--;
                    newMatrix[whiteCurrentRow][whiteCurrentCol] = "w";
                }
            } else {
                //black
                if ((blackCurrentRow + 1) == 7) {
                    // has reached the end
                    newMatrix[blackCurrentRow][blackCurrentCol] = "-";
                    blackCurrentRow++;
                    System.out.printf("Game over! Black pawn is promoted to a queen at %s.\n", matrix[blackCurrentRow][blackCurrentCol]);
                    return;
                }
                if (isInBounds(newMatrix, blackCurrentRow + 1, blackCurrentCol - 1) && newMatrix[blackCurrentRow + 1][blackCurrentCol - 1].equals("w")) {
                    newMatrix[blackCurrentRow][blackCurrentCol] = "-";
                    blackCurrentRow++;
                    blackCurrentCol--;
                    newMatrix[blackCurrentRow][blackCurrentCol] = "b";
                    System.out.printf("Game over! Black capture on %s.\n", matrix[blackCurrentRow][blackCurrentCol]);
                    return;
                } else if (isInBounds(newMatrix, blackCurrentRow + 1, blackCurrentCol + 1) && newMatrix[blackCurrentRow + 1][blackCurrentCol + 1].equals("w")) {
                    newMatrix[blackCurrentRow][blackCurrentCol] = "-";
                    blackCurrentRow++;
                    blackCurrentCol++;
                    newMatrix[blackCurrentRow][blackCurrentCol] = "b";
                    System.out.printf("Game over! Black capture on %s.\n", matrix[blackCurrentRow][blackCurrentCol]);
                    return;
                }
                if (isInBounds(matrix, blackCurrentRow + 1, blackCurrentCol)) {
                    newMatrix[blackCurrentRow][blackCurrentCol] = "-";
                    blackCurrentRow++;
                    newMatrix[blackCurrentRow][blackCurrentCol] = "b";
                }
            }


        }
    }

    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static int[] findInitialPosition(String[][] matrix, String letter) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals(letter)) {
                    initialRow = row;
                    initialCol = col;
                    break;
                }
            }
        }
        return new int[]{initialRow, initialCol};
    }

    private static void fillMatrix(String[][] matrix, Scanner scan) {
        for (int row = 0; row < matrix.length; row++) {
            String[] data = scan.nextLine().split("");
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = data[col];
            }
        }
    }

    public static void fillMatrixChess(String[][] matrix, char letter, int number) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = String.valueOf(letter) + String.valueOf(number);
                letter++;
            }
            letter = 'a';
            number--;
        }
    }
}
