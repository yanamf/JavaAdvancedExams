package MultiArray_02;

import java.util.Scanner;

public class MouseAndCheese {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrix(matrix, scanner);

        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        matrix[currentRow][currentCol] = "-";

        int eatenCheese = 0;
        boolean isOutOfTerritory = false;

        String input = scanner.nextLine();

        while (!input.equals("end")) {
            if (input.equals("up")) {
                if (currentRow - 1 >= 0) {
                    //isInBounds
                    currentRow--;
                } else {
                    isOutOfTerritory = true;
                    break;
                }

            } else if (input.equals("down")) {
                if (currentRow + 1 < sizeMatrix) {
                    //isInBounds
                    currentRow++;
                } else {
                    isOutOfTerritory = true;
                    break;
                }
            } else if (input.equals("left")) {
                if (currentCol - 1 >= 0) {
                    //isInBounds
                    currentCol--;
                } else {
                    isOutOfTerritory = true;
                    break;
                }

            } else if (input.equals("right")) {
                if (currentCol + 1 < matrix[0].length) {
                    //isInBounds
                    currentCol++;
                } else {
                    isOutOfTerritory = true;
                    break;
                }
            }
            if (matrix[currentRow][currentCol].equals("c")) {
                eatenCheese++;
                matrix[currentRow][currentCol] = "-";

            } else if (matrix[currentRow][currentCol].equals("B")) {
                matrix[currentRow][currentCol] = "-";
                continue;
            }
            input = scanner.nextLine();
        }
        if (isOutOfTerritory){
            System.out.println("Where is the mouse?");
        } else {
            matrix[currentRow][currentCol] = "M";
        }
        if (eatenCheese < 5){
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.\n", 5 - eatenCheese);
        } else {
            System.out.printf("Great job, the mouse is fed %d cheeses!\n", eatenCheese);
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
    public static int[] findInitialPosition(String[][] matrix) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("M")) {
                    initialRow = row;
                    initialCol = col;
                    break;
                }
            }
        }
        return new int[]{initialRow, initialCol};

    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split("");
            matrix[i] = currentRow;
        }
    }
}
