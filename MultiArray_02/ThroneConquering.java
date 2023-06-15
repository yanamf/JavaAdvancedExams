package MultiArray_02;

import java.util.Scanner;

public class ThroneConquering {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int energy = Integer.parseInt(scanner.nextLine());
        int sizeMatrix = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[sizeMatrix][];
        fillMatrix(matrix, scanner);
        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        boolean isDead = false;
        boolean isHelenAbducted = false;

        String command = scanner.nextLine();
        while (true) {
            String[] commandSplit = command.split("\\s+");
            String direction = commandSplit[0];

            int enemyRow = Integer.parseInt(commandSplit[1]);
            int enemyCol = Integer.parseInt(commandSplit[2]);

            matrix[enemyRow][enemyCol] = "S";
            matrix[currentRow][currentCol] = "-";
            energy--;

            if (direction.equals("up")) {
                if (currentRow - 1 >= 0) {
                    //isInBounds
                    currentRow--;
                }

            } else if (direction.equals("down")) {
                if (currentRow + 1 < sizeMatrix) {
                    //isInBounds
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 >= 0) {
                    //isInBounds
                    currentCol--;
                }

            } else if (direction.equals("right")) {
                if (currentCol + 1 < matrix[0].length) {
                    //isInBounds
                    currentCol++;
                }
            }
            // check position

            if (matrix[currentRow][currentCol].equals("S")) {
                energy -= 2;
                if (energy <= 0) {
                    //dies
                    isDead = true;
                    matrix[currentRow][currentCol] = "X";
                    break;
                } else {
                    // paris kills the enemy
                    matrix[currentRow][currentCol] = "-";
                }
            } else if (matrix[currentRow][currentCol].equals("H")) {
                //finds Helen and abducts her
                matrix[currentRow][currentCol] = "-";
                isHelenAbducted = true;
                break;
            }
            if (energy <= 0) {
                isDead = true;
                matrix[currentRow][currentCol] = "X";
                break;
            }
            command = scanner.nextLine();
        }
        if (isDead) {
            System.out.printf("Paris died at %d;%d.\n", currentRow, currentCol);
        }
        if (isHelenAbducted) {
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d\n", energy);
        }
        printMatrix(matrix);

    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }


    public static int[] findInitialPosition(String[][] matrix) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("P")) {
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
