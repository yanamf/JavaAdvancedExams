import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnergyDrinks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> mgCaffeine = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(String::trim)
                .map(Integer::parseInt)
                .forEach(mgCaffeine::add);

        ArrayDeque<Integer> energyDrinks = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(String::trim)
                .map(Integer::parseInt)
                .forEach(energyDrinks::add);


        int totalCaffeine = 0;

        while (!mgCaffeine.isEmpty() && !energyDrinks.isEmpty()) {
            int result = mgCaffeine.getLast() * energyDrinks.getFirst();
            if (result + totalCaffeine <= 300) {
                totalCaffeine += result;
                mgCaffeine.removeLast();
                energyDrinks.removeFirst();
            } else {
                mgCaffeine.removeLast();
                int temporary = energyDrinks.removeFirst();
                energyDrinks.addLast(temporary);
                if (totalCaffeine - 30 >= 0) {
                    totalCaffeine -= 30;
                } else {
                    totalCaffeine = 0;
                }
            }
        }
        if (!energyDrinks.isEmpty()){
            System.out.print("Drinks left: ");
            System.out.print(energyDrinks.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        } else {
            System.out.println
                    ("At least Stamat wasn't exceeding the maximum caffeine.");
        }
        System.out.printf
                ("Stamat is going to sleep with %d mg caffeine.", totalCaffeine);
    }
}
