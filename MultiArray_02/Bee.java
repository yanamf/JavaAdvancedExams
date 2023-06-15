package MultiArray_02;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Bee {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sizeMatrix = Integer.parseInt(scan.nextLine());
        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrix(matrix, scan);
        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];

        String command = scan.nextLine();
        int pollinatedFlowers = 0;
        boolean outOfTerritory = false;

        while (!command.equals("End")) {
            int temporaryRow = currentRow;
            int temporaryCol = currentCol;
            matrix[currentRow][currentCol] = ".";
            if (command.equals("up")) {
                temporaryRow--;
            } else if (command.equals("down")) {
                temporaryRow++;
            } else if (command.equals("left")) {
                temporaryCol--;
            } else if (command.equals("right")) {
                temporaryCol++;
            }
            if (isInBounds(matrix, temporaryRow, temporaryCol)) {
                currentRow = temporaryRow;
                currentCol = temporaryCol;
                if (matrix[currentRow][currentCol].equals("f")) {
                    pollinatedFlowers++;
                } else if (matrix[currentRow][currentCol].equals("O")) {
                    //bonus
                    matrix[currentRow][currentCol] = "."; //bonus disappears
                    if (command.equals("up")) {
                        currentRow--;
                    } else if (command.equals("down")) {
                        currentRow++;
                    } else if (command.equals("left")) {
                        currentCol--;
                    } else if (command.equals("right")) {
                        currentCol++;
                    }
                    if (matrix[currentRow][currentCol].equals("f")){
                        pollinatedFlowers++;
                    }
                }
                matrix[currentRow][currentCol] = "B";
            } else {
                outOfTerritory = true;
                break;
            }
            command = scan.nextLine();
        }
        if (outOfTerritory){
            System.out.println("The bee got lost!");
        }
        if (pollinatedFlowers < 5){
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more\n", 5 - pollinatedFlowers);
        } else {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!\n", pollinatedFlowers);
        }
        printMatrix(matrix);
    }

    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static int[] findInitialPosition(String[][] matrix) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col].equals("B")) {
                    initialRow = row;
                    initialCol = col;
                    break;
                }
            }
        }
        return new int[]{initialRow, initialCol};
    }
    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String[] parts = scanner.nextLine().split("");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = parts[col];
            }
        }
    }
}
