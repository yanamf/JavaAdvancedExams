package MultiArray_02;

import java.util.Scanner;

public class NavyBattle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];
        fillMatrix(matrix, scanner);

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("S")) {
                    currentRow = row;
                    currentCol = col;
                    matrix[currentRow][currentCol] = "-";
                }
            }
        }
        int countHits = 0;
        int countCruiser = 0;
        String currentPosition = "";
        while (true) {
            String direction = scanner.nextLine();

            if (direction.equals("up")) {
                currentRow = currentRow - 1;
                currentPosition = matrix[currentRow][currentCol];
            } else if (direction.equals("down")) {
                currentRow = currentRow + 1;
                currentPosition = matrix[currentRow][currentCol];
            } else if (direction.equals("left")) {
                currentCol = currentCol - 1;
                currentPosition = matrix[currentRow][currentCol];
            } else if (direction.equals("right")) {
                currentCol = currentCol + 1;
                currentPosition = matrix[currentRow][currentCol];
            }

            if (currentPosition.equals("-")) {
                matrix[currentRow][currentCol] = currentPosition;
            } else if (currentPosition.equals("*")) {
                countHits++;
                if (countHits == 3) {
                    currentPosition = "S";
                    matrix[currentRow][currentCol] = currentPosition;
                    System.out.printf
                            ("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!\n", currentRow, currentCol);
                    break;
                }
                currentPosition = "-";
                matrix[currentRow][currentCol] = currentPosition;

            } else if (currentPosition.equals("C")) {
                currentPosition = "-";
                matrix[currentRow][currentCol] = currentPosition;
                countCruiser++;
                if (countCruiser == 3) {
                    matrix[currentRow][currentCol] = "S";
                    System.out.print
                            ("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!\n");
                    break;
                }
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }


    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split("");
            matrix[i] = currentRow;
        }
    }
}
