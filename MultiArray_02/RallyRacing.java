package MultiArray_02;

import java.util.Scanner;

public class RallyRacing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sizeMatrix = Integer.parseInt(scanner.nextLine());
        String numberOfCar = scanner.nextLine();

        String[][] matrix = new String[sizeMatrix][sizeMatrix];
        fillMatrix(matrix, scanner);
        String command = scanner.nextLine();

        int currentRow = 0;
        int currentCol = 0;

        int distance = 0;

        boolean isFinished = false;

        while (!command.equals("End")) {

            if (command.equals("up")) {
                currentRow -= 1;
            } else if (command.equals("down")) {
                currentRow += 1;
            } else if (command.equals("left")) {
                currentCol -= 1;
            } else if (command.equals("right")) {
                currentCol += 1;
            }
            if (matrix[currentRow][currentCol].equals("T")) {
                distance += 30;
                matrix[currentRow][currentCol] = ".";
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (matrix[i][j].equals("T")){
                            currentRow = i;
                            currentCol = j;
                            matrix[i][j] = ".";
                        }
                    }
                }
            } else if (matrix[currentRow][currentCol].equals("F")){
                isFinished = true;
                distance += 10;
                matrix[currentRow][currentCol] = "C";
                break;
            } else if (matrix[currentRow][currentCol].equals(".")) {
                distance += 10;
            }

            command = scanner.nextLine();
        }
        if (isFinished){
            System.out.printf("Racing car %s finished the stage!\n", numberOfCar);
        } else {
            matrix[currentRow][currentCol] = "C";
            System.out.printf("Racing car %s DNF.\n", numberOfCar);
        }
        System.out.printf("Distance covered %d km.\n", distance);
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

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            String[] currentRow = scanner.nextLine().split("\\s+");
            matrix[i] = currentRow;
        }
    }
}
