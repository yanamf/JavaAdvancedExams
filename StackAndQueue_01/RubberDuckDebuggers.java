import java.util.*;
import java.util.stream.Collectors;

public class RubberDuckDebuggers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> duckTypeCount = new LinkedHashMap<>();
        duckTypeCount.put("Darth Vader Ducky", 0);
        duckTypeCount.put("Thor Ducky", 0);
        duckTypeCount.put("Big Blue Rubber Ducky", 0);
        duckTypeCount.put("Small Yellow Rubber Ducky", 0);


        ArrayDeque<Integer> times = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> tasks = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));


        while (!times.isEmpty() && !tasks.isEmpty()) {
            int result = times.getFirst() * tasks.getLast();
            if (result >= 0 && result <= 60) {
                duckTypeCount.put("Darth Vader Ducky", duckTypeCount.get("Darth Vader Ducky") + 1);
            } else if (result <= 120) {
                duckTypeCount.put("Thor Ducky", duckTypeCount.get("Thor Ducky") + 1);
            } else if (result <= 180) {
                duckTypeCount.put("Big Blue Rubber Ducky", duckTypeCount.get("Big Blue Rubber Ducky") + 1);
            } else if (result <= 240) {
                duckTypeCount.put("Small Yellow Rubber Ducky", duckTypeCount.get("Small Yellow Rubber Ducky") + 1);
            } else {
                int toBeAdded = times.removeFirst();
                times.addLast(toBeAdded);
                int last = tasks.removeLast();
                tasks.addLast(last - 2);
                continue;
            }
            times.removeFirst();
            tasks.removeLast();
        }
        System.out.println("Congratulations, all tasks have been completed! Rubber ducks rewarded:");
        for (Map.Entry<String, Integer> entry : duckTypeCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

    }

}
