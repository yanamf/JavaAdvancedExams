import java.util.*;
import java.util.stream.Collectors;

public class ClimbThePeaks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> foodStack = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> staminaQueue = Arrays.stream(scanner.nextLine().split(",\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<String> peaks = new ArrayList<>();

        int cycle = 0;
        while ((cycle != 7) && (peaks.size() < 5)) {
            cycle++;
            int result = foodStack.getLast() + staminaQueue.getFirst();
            foodStack.removeLast();
            staminaQueue.removeFirst();

            if (result >= 80 && !peaks.contains("Vihren")) {
                peaks.add("Vihren");
            } else if (result >= 90 && !peaks.contains("Kutelo") && peaks.size() == 1) {
                peaks.add("Kutelo");
            } else if (result >= 100 && !peaks.contains("Banski Suhodol") && peaks.size() == 2) {
                peaks.add("Banski Suhodol");
            } else if (result >= 60 && !peaks.contains("Polezhan") && peaks.size() == 3) {
                peaks.add("Polezhan");
            } else if (result >= 70 && !peaks.contains("Kamenitza") && peaks.size() == 4) {
                peaks.add("Kamenitza");
            }
        }

        if (peaks.size() == 5) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }

        if (!peaks.isEmpty()) {
            System.out.println("Conquered peaks:");
            for (String peak : peaks) {
                System.out.println(peak);
            }
        }
    }
}
