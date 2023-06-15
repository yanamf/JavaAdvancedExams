import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KAT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> licensePlates = Arrays.stream(scanner.nextLine().split(",\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> cars = Arrays.stream(scanner.nextLine().split(",\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        int countCars = 0;
        int result;
        int cycle = 0;
        while (!licensePlates.isEmpty() && !cars.isEmpty()) {
            cycle++;
            int currentCars = cars.getLast();
            int currentLicencePlates = licensePlates.getFirst();

            if (currentCars > currentLicencePlates) {
                //50 cars and 20 plates
                result = currentLicencePlates / 2; // 20/2 = 10
                if (result == currentCars) { // if 10 == 50
                    licensePlates.removeFirst();
                    cars.removeLast();
                    countCars += result;
                } else if (result < currentCars) { // 10 < 50
                    int countCarsLeft = currentCars - result;
                    licensePlates.removeFirst();
                    cars.removeLast();
                    cars.addFirst(countCarsLeft);
                    countCars += result;
                }

            } else if (currentCars * 2 <= currentLicencePlates) {
                // 33 cars and 100 plates
                result = currentCars * 2; //33 cars and 66 plates
                int remainingLicensePlates = currentLicencePlates - result;

                if (remainingLicensePlates > 0) {
                    licensePlates.removeFirst();
                    licensePlates.addLast(remainingLicensePlates);
                    cars.removeLast();
                } else if (remainingLicensePlates == 0) {
                    licensePlates.removeFirst();
                    cars.removeLast();
                }
                countCars += currentCars;
            } else {
                //cars.getLast() == licensePlates.getFirst()
                // 40 and 40
                result = currentLicencePlates / 2;
                countCars += result;
                cars.removeLast();
                licensePlates.removeFirst();
                cars.addFirst(currentCars - result);
            }
        }
        System.out.printf("%d cars were registered for %d days!\n", countCars, cycle);
        if (!licensePlates.isEmpty()) {
            int licensePlatesLeft = 0;
            for (Integer count : licensePlates) {
                licensePlatesLeft += count;
            }
            System.out.printf("%d license plates remain!\n", licensePlatesLeft);
        }
        if (!cars.isEmpty()) {
            int carsLeft = 0;
            for (Integer count : cars) {
                carsLeft += count;
            }
            System.out.printf("%d cars remain without license plates!\n", carsLeft);
        }
        if (cars.isEmpty() && licensePlates.isEmpty()) {
            System.out.println("Good job! There is no queue in front of the KAT!");
        }
    }
}
