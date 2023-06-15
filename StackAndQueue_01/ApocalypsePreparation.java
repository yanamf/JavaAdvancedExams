import java.util.*;
import java.util.stream.Collectors;

public class ApocalypsePreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> textile = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> medicament = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        Map<String, Integer> items = new TreeMap<>();


        while (!textile.isEmpty() && !medicament.isEmpty()) {

            int result = textile.getFirst() + medicament.getLast();
            if (result == 30) {
                textile.removeFirst();
                medicament.removeLast();
                items.putIfAbsent("Patch", 0);
                items.put("Patch", items.get("Patch") + 1);
            } else if (result == 40) {
                textile.removeFirst();
                medicament.removeLast();
                items.putIfAbsent("Bandage", 0);
                items.put("Bandage", items.get("Bandage") + 1);
            } else if (result == 100) {
                textile.removeFirst();
                medicament.removeLast();
                items.putIfAbsent("MedKit", 0);
                items.put("MedKit", items.get("MedKit") + 1);
            } else if (result > 100) {
                int remaining = result - 100;
                textile.removeFirst();
                medicament.removeLast();
                int firstAfterRemoval = medicament.removeLast();
                medicament.addLast(remaining + firstAfterRemoval);
                items.putIfAbsent("MedKit", 0);
                items.put("MedKit", items.get("MedKit") + 1);
            } else {
                textile.removeFirst();
                int firstAfterRemoval = medicament.removeLast();
                medicament.addLast(firstAfterRemoval + 10);
            }
        }
        if (textile.isEmpty() && medicament.isEmpty()) {
            System.out.println("Textiles and medicaments are both empty.");
        } else if (textile.isEmpty()) {
            System.out.println("Textiles are empty.");
        } else {
            System.out.println("Medicaments are empty.");
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(items.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()));

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.printf("%s - %d\n", entry.getKey(), entry.getValue());
        }


        if (!textile.isEmpty()) {
            System.out.print("Textiles left: ");
            System.out.print(textile.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
        } else if (!medicament.isEmpty()) {
            System.out.print("Medicaments left: ");
            List<String> sortedMedicaments = new ArrayList<>(medicament.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList()));
            Collections.reverse(sortedMedicaments); // Reverse the list
            System.out.print(sortedMedicaments.stream().collect(Collectors.joining(", ")));
        }
    }
}
