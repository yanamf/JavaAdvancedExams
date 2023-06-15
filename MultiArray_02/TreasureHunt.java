package MultiArray_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(" ");
        int row = Integer.parseInt(dimensions[0]);
        int col = Integer.parseInt(dimensions[1]);

        String[][] matrix = new String[row][col];
        fillMatrix(matrix, scanner);
        List<String> directions = new ArrayList<>();
        int currentRow = -1;
        int currentCol = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j].equals("Y")) {
                    currentRow = i;
                    currentCol = j;
                    break;
                }
            }
        }
        String input = scanner.nextLine();
        int temporaryRow = currentRow;
        int temporaryCol = currentCol;
        while (!input.equals("Finish")) {
            if (input.equals("up")) {
                temporaryRow = currentRow - 1;
            } else if (input.equals("down")) {
                temporaryRow = currentRow + 1;
            } else if (input.equals("left")) {
                temporaryCol = currentCol - 1;
            } else if (input.equals("right")) {
                temporaryCol = currentCol + 1;
            }

            if (isInBounds(matrix, temporaryRow, temporaryCol)) {
                String currentPosition = matrix[temporaryRow][temporaryCol];
                if (currentPosition.equals("T")) {
                    temporaryRow = currentRow;
                    temporaryCol = currentCol;
                } else if (currentPosition.equals("X")) {
                    currentRow = temporaryRow;
                    currentCol = temporaryCol;
                    directions.add(input);
                } else if (currentPosition.equals("-")) {
                    currentRow = temporaryRow;
                    currentCol = temporaryCol;
                    directions.add(input);
                }

            }

            input = scanner.nextLine();
        }
        if (matrix[currentRow][currentCol].equals("X")) {
            System.out.print("I've found the treasure!\n");
            System.out.print("The right path is ");
            System.out.print(String.join(", ", directions));
        } else {
            System.out.println("The map is fake!");
        }
    }


    private static boolean isInBounds(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    //    private static void fillMatrix(String[][] matrix, Scanner scanner) {
//        for (int i = 0; i < matrix.length; i++) {
//            String[] currentR = scanner.nextLine().split(" ");
//            matrix[i] = currentR;
//        }
//    }
    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String[] data = scanner.nextLine().split("\\s+");
            for (int col = 0; col < matrix[row].length; col++) {
                //the given row data length may not be equal to the cols(is less and must be refilled)
                if (col < data.length) {
                    matrix[row][col] = data[col];
                }else{
                    matrix[row][col] = "-";
                }
            }
        }
    }


}
