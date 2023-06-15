package MultiArray_02;

import java.util.Scanner;

public class CookingJourney {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sizeMatrix = Integer.parseInt(scan.nextLine());
        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrix(matrix, scan);
        int currentRow = findInitialPosition(matrix)[0];
        int currentCol = findInitialPosition(matrix)[1];
        int moneyCollected = 0;
        boolean isOutOfPastryShop = false;
        while (true){
            String direction = scan.nextLine();
            matrix[currentRow][currentCol] = "-";
            if (direction.equals("up")) {
                currentRow--;
            } else if (direction.equals("down")) {
                currentRow++;
            } else if (direction.equals("left")) {
                currentCol--;
            } else if (direction.equals("right")) {
                currentCol++;
            }
            if (isInBounds(matrix, currentRow, currentCol)){

                if (matrix[currentRow][currentCol].matches("\\d")){
                    moneyCollected += Integer.parseInt(matrix[currentRow][currentCol]);
                    matrix[currentRow][currentCol] = "-";
                    if (moneyCollected >= 50){
                        // program ends
                        matrix[currentRow][currentCol] = "S";
                        break;
                    }
                } else if (matrix[currentRow][currentCol].equals("P")) {
                    matrix[currentRow][currentCol] = "-";
                    for (int row = 0; row < matrix.length; row++) {
                        for (int col = 0; col < matrix[row].length; col++) {
                            if (matrix[row][col].equals("P")) {
                                currentRow = row;
                                currentCol = col;
                                matrix[row][col] = "-";
                            }
                        }
                    }
                }
            } else {
                // program ends
                isOutOfPastryShop = true;
                break;
            }
        }
        if (isOutOfPastryShop){
            System.out.println("Bad news! You are out of the pastry shop.");
        } else {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.printf("Money: %d\n", moneyCollected);
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
    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
    public static int[] findInitialPosition(String[][] matrix) {
        int initialRow = -1;
        int initialCol = -1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("S")) {
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
}
