import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class KATAuthorsSolution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Long> licensePlates = Arrays.stream(reader.readLine().split(", ")).map(Long::parseLong).collect(Collectors.toCollection(ArrayDeque::new));
        Deque<Long> cars = new ArrayDeque<>();
        Arrays.stream(reader.readLine().split(", ")).map(Long::parseLong).forEach(cars::push);

        long countCars = 0;
        long days = 0;
        while (!licensePlates.isEmpty() && !cars.isEmpty()) {
            days++;
            long currentLicencePlates = 0;
            for (int i = 0; i < 1; i++) {
                currentLicencePlates = licensePlates.poll();
            }

            long currentCars = 0;
            for (int i = 0; i < 1; i++) {
                currentCars = cars.pop();
            }

            if (currentCars * 2 > currentLicencePlates) {
                //50 cars and 20 plates
                long leftCars = currentCars - (currentLicencePlates / 2);
                cars.addLast(leftCars);

                countCars += currentLicencePlates / 2;

            } else if (currentCars * 2 < currentLicencePlates) {
                // 33 cars and 100 plates
                licensePlates.addLast(currentLicencePlates - currentCars * 2);
                countCars += currentCars;
            } else {
                countCars += currentCars;
            }
        }
        System.out.printf("%d cars were registered for %d days!\n", countCars, days);
        if (!licensePlates.isEmpty()){
            long sumOfUnplacedPlates = 0;
            while (!licensePlates.isEmpty()) {
                sumOfUnplacedPlates += licensePlates.poll();
            }
            System.out.printf("%d license plates remain!\n", sumOfUnplacedPlates);
        }
        if (!cars.isEmpty()){
            long sumOfCarsWithoutPlate = 0;
            while (!cars.isEmpty()) {
                sumOfCarsWithoutPlate += cars.pop();
            }
            System.out.printf("%d cars remain without license plates!", sumOfCarsWithoutPlate);
        }
        if (cars.isEmpty() && licensePlates.isEmpty()){
            System.out.println("Good job! There is no queue in front of the KAT!");
        }
    }
}
