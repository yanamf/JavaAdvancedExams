import java.util.*;
import java.util.stream.Collectors;

public class ClimbThePeaksAuthorsSolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> peaks = new ArrayDeque<>(Arrays.asList("Vihren", "Kutelo", "Banski Suhodol", "Polezhan", "Kamenitza"));
        HashMap<String, Integer> peakAndLevel = new LinkedHashMap<>(Map.of("Vihren", 80,
                "Kutelo", 90,
                "Banski Suhodol", 100,
                "Polezhan", 60,
                "Kamenitza", 70));

        List<String> conqueredPeaks = new ArrayList<>();
        ArrayDeque<Integer> foodPortions = Arrays.stream(scanner.nextLine().split(",\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> staminaQuan = Arrays.stream(scanner.nextLine().split(",\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        while (!peakAndLevel.isEmpty() && !foodPortions.isEmpty() && !staminaQuan.isEmpty()) {
            String currentPeakName = peaks.getFirst();
            if (foodPortions.getLast() + staminaQuan.getFirst() >= peakAndLevel.get(currentPeakName)) {
                conqueredPeaks.add(currentPeakName);
                staminaQuan.removeFirst();
                foodPortions.removeLast();
                peakAndLevel.remove(currentPeakName);
                peaks.remove(currentPeakName);
            } else {
                staminaQuan.removeFirst();
                foodPortions.removeLast();
            }
        }

        if (peakAndLevel.isEmpty()) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }

        if (!conqueredPeaks.isEmpty()) {
            System.out.println("Conquered peaks:");
            System.out.println(conqueredPeaks.stream().map(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
        }
    }
}
