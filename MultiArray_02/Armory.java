package MultiArray_02;

import java.util.Scanner;

public class Armory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[n][n];

        fillMatrix(matrix, scanner);

        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        matrix[currentRow][currentCol] = "-";

        int goldCoins = 0;
        boolean outOfArmory = false;

        String direction = scanner.nextLine();
        while (goldCoins < 65) {
            switch (direction) {
                case "up":
                    currentRow--;
                    if (currentRow < 0) {
                        outOfArmory = true;
                    }
                    break;
                case "down":
                    currentRow++;
                    if (currentRow > n - 1) {
                        outOfArmory = true;
                    }
                    break;
                case "right":
                    currentCol++;
                    if (currentCol > n - 1) {
                        outOfArmory = true;
                        break;
                    }
                    break;
                case "left":
                    currentCol--;
                    if (currentCol < 0) {
                        outOfArmory = true;
                    }
                    break;
            }
            if (outOfArmory) {
                break;
            }

            String currentEl = matrix[currentRow][currentCol];
            // digits
            if (Character.isDigit(currentEl.charAt(0))) {
                goldCoins += Integer.parseInt(currentEl);
                matrix[currentRow][currentCol] = "-";

            } else if (currentEl.equals("M")) {
                matrix[currentRow][currentCol] = "-";
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (matrix[i][j].equals("M")) {
                            currentRow = i;
                            currentCol = j;
                            matrix[i][j] = "-";
                        }
                    }
                }
            }
            if (goldCoins >= 65) {
                break;
            }
            direction = scanner.nextLine();
        }
        if (outOfArmory) {
            System.out.println("I do not need more swords!");
        } else {
            System.out.println("Very nice swords, I will come back for more!");
            matrix[currentRow][currentCol] = "A";
        }
        System.out.printf("The king paid %d gold coins.\n", goldCoins);
        printMatrix(matrix);
    }

    public static int[] findInitialPosition(String[][] matrix) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col].equals("A")) {
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
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split("");
            matrix[i] = currentRow;
        }
    }
}