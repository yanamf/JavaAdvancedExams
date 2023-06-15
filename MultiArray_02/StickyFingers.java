package MultiArray_02;

import java.io.IOException;
import java.util.Scanner;

public class StickyFingers {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(",");
        String[][] matrix = new String[n][n];
        fillMatrix(matrix, scanner);
        System.out.println();
        int currentRow = -1;
        int currentCol = -1;

        int totalStolenQuantity = 0;
        boolean hasBeenCaught = false;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("D")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }
        for (int i = 0; i < directions.length; i++) {
            String currentDirection = directions[i];
            int temporaryRow = currentRow;
            int temporaryCol = currentCol;
            switch (currentDirection) {
                case "up":
                    temporaryRow--;
                    break;
                case "down":
                    temporaryRow++;
                    break;
                case "left":
                    temporaryCol--;
                    break;
                case "right":
                    temporaryCol++;
                    break;

            }
            if (isInBounds(matrix, temporaryRow, temporaryCol)){
                matrix[currentRow][currentCol] = "+";
                currentRow = temporaryRow;
                currentCol = temporaryCol;

                if (matrix[temporaryRow][temporaryCol].equals("$")) {

                    matrix[currentRow][currentCol] = "+";
                    int stolenQuantity = currentRow * currentCol;
                    totalStolenQuantity += stolenQuantity;

                    System.out.printf("You successfully stole %d$.\n", stolenQuantity);

                } else if (matrix[temporaryRow][temporaryCol].equals("P")) {
                    matrix[currentRow][currentCol] = "#";
                    hasBeenCaught = true;
                    break;
                }
            } else {
                System.out.println("You cannot leave the town, there is police outside!");
            }
            matrix[currentRow][currentCol] = "D";
        }
        if (hasBeenCaught){
            System.out.printf("You got caught with %d$, and you are going to jail.\n", totalStolenQuantity);
        } else {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.\n", totalStolenQuantity);
        }
        printMatrix(matrix);
    }
    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split(" ");
            matrix[i] = currentRow;
        }
    }
}
