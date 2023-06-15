package MultiArray_02;

import java.util.Scanner;

public class Python {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scan.nextLine());
        String[] directions = scan.nextLine().split(",\\s+");
        String[][] matrix = new String[sizeMatrix][sizeMatrix];

        fillMatrix(matrix, scan);
        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        int snakeLength = 1;
        int foodOnField = countFoodLeft(matrix);

        for (int i = 0; i < directions.length; i++) {
            String direction = directions[i];

            if (direction.equals("up")) {
                currentRow--;
                if (currentRow < 0) {
                    currentRow = matrix.length - 1;
                }

            } else if (direction.equals("down")) {
                currentRow++;
                if (currentRow >= matrix.length) {
                    currentRow = 0;
                }
            } else if (direction.equals("left")) {
                //side edge
                currentCol--;
                if (currentCol < 0) {
                    currentCol = matrix.length - 1;
                }
            } else if (direction.equals("right")) {
                //side edge
                currentCol++;
                if (currentCol >= matrix[0].length) {
                    currentCol = 0;
                }
            }
            if (matrix[currentRow][currentCol].equals("e")) {
                System.out.println("You lose! Killed by an enemy!");
                return;
            } else if (matrix[currentRow][currentCol].equals("f")) {
                matrix[currentRow][currentCol] = "*";
                snakeLength++;
                foodOnField--;
                if (foodOnField == 0) {
                    System.out.printf("You win! Final python length is %d\n", snakeLength);
                   return;
                }
            }
        }
        System.out.printf("You lose! There is still %d food to be eaten.\n", foodOnField);

    }

    public static int countFoodLeft(String[][] matrix) {
        int foodCount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col].equals("f")) {
                    foodCount++;
                }
            }
        }
        return foodCount;
    }


    public static int[] findInitialPosition(String[][] matrix) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("s")) {
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
            String[] data = scanner.nextLine().split("\\s+");
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = data[col];
            }
        }
    }
}
