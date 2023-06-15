package MultiArray_02;

import java.util.Scanner;

public class Bomb {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scan.nextLine());
        String[] directions = scan.nextLine().split(",");
        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrix(matrix, scan);
        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        matrix[currentRow][currentCol] = "+";
        int countBombsLeft = countBombsTotal(matrix);
        for (int i = 0; i < directions.length; i++) {

            if (directions[i].equals("up")){
                if (currentRow - 1 >= 0){
                    currentRow--;
                }
            } else if (directions[i].equals("down")) {
                if (currentRow + 1 < matrix.length){
                    currentRow++;
                }
                
            } else if (directions[i].equals("left")) {
                if (currentCol - 1 >= 0){
                    currentCol--;
                }
            } else if (directions[i].equals("right")) {
                if (currentCol + 1 < matrix.length){
                    currentCol++;
                }
            }
            if (matrix[currentRow][currentCol].equals("B")){
                countBombsLeft--;
                matrix[currentRow][currentCol] = "+";
                System.out.println("You found a bomb!");
                if (countBombsLeft == 0){
                    System.out.println("Congratulations! You found all bombs!");
                    return;
                }
            } else if (matrix[currentRow][currentCol].equals("e")) {
                System.out.printf("END! %d bombs left on the field\n", countBombsLeft);
                return;
            }
        }
        System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)", countBombsLeft, currentRow, currentCol);
    }
    public static int countBombsTotal(String[][] matrix) {
        int countBombs = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col].equals("B")) {
                    countBombs++;
                }
            }
        }
        return countBombs;
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

    private static void fillMatrix(String[][] matrix, Scanner scan) {
        for (int row = 0; row < matrix.length; row++) {
            String[] data = scan.nextLine().split("\\s+");
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = data[col];
            }
        }
    }
}
