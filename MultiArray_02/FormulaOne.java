package MultiArray_02;

import java.util.Scanner;

public class FormulaOne {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scan.nextLine());
        int countCommands = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrix(matrix, scan);
        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        boolean hasFinished = false;

        for (int i = 0; i < countCommands; i++) {
            String direction = scan.nextLine();
            int temporaryRow = currentRow;
            int temporaryCol = currentCol;
            if (direction.equals("up")) {
                if (temporaryRow - 1 >= 0) {
                    //isInBounds
                    temporaryRow--;
                } else {
                    temporaryRow = matrix.length - 1;
                }

            } else if (direction.equals("down")) {
                if (temporaryRow + 1 < sizeMatrix) {
                    //isInBounds
                    temporaryRow++;
                } else {
                    temporaryRow = 0;
                }
            } else if (direction.equals("left")) {
                if (temporaryCol - 1 >= 0) {
                    //isInBounds
                    temporaryCol--;
                } else {
                    // goes on other side
                    temporaryCol = matrix.length - 1;
                }

            } else if (direction.equals("right")) {
                if (temporaryCol + 1 < matrix[0].length) {
                    //isInBounds
                    temporaryCol++;
                } else {
                    temporaryCol = 0;

                }
            }
            if (matrix[temporaryRow][temporaryCol].equals("B")) {
                matrix[currentRow][currentCol] = ".";
                currentRow = temporaryRow;
                currentCol = temporaryCol;
                //bonus (go a step forward)
                if (direction.equals("up")) {
                    currentRow--;
                    if (currentRow < 0) {
                        currentRow = matrix.length - 1;
                    }
                } else if (direction.equals("down")) {
                    currentRow++;
                    if (currentRow > matrix.length - 1) {
                        currentRow = 0;
                    }
                } else if (direction.equals("right")) {
                    currentCol++;
                    if (currentCol > matrix.length - 1) {
                        currentCol = 0;
                    }
                } else if (direction.equals("left")) {
                    currentCol--;
                    if (currentCol < 0) {
                        currentCol = matrix.length - 1;
                    }
                }
            } else if (matrix[temporaryRow][temporaryCol].equals("T")) {
                //trap
                temporaryRow = currentRow;
                temporaryCol = currentCol;
            } else if (matrix[temporaryRow][temporaryCol].equals("F")) {
                matrix[currentRow][currentCol] = ".";
                currentRow = temporaryRow;
                currentCol = temporaryCol;
                matrix[currentRow][currentCol] = "P";
                hasFinished = true;
                break;
            } else if (matrix[temporaryRow][temporaryCol].equals(".")) {
                matrix[currentRow][currentCol] = ".";
                currentRow = temporaryRow;
                currentCol = temporaryCol;
            }
            matrix[currentRow][currentCol] = "P";
        }
        if (hasFinished) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
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
        for (int row = 0; row < matrix.length; row++) {
            String[] data = scanner.nextLine().split("");
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = data[col];
            }
        }
    }
}
