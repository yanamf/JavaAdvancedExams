import java.util.*;
import java.util.stream.Collectors;

public class ItsChocolateTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Double> milkValues = Arrays.stream(scanner.nextLine().split("\\s+")).map(Double::parseDouble).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Double> cacaoPowderValues = Arrays.stream(scanner.nextLine().split("\\s+")).map(Double::parseDouble).collect(Collectors.toCollection(ArrayDeque::new));

        Map<String, Integer> chocolateMade = new TreeMap<>();
        while (!milkValues.isEmpty() && !cacaoPowderValues.isEmpty()) {

            double cacaoPercentage = cacaoPowderValues.getLast() / (milkValues.getFirst() + cacaoPowderValues.getLast()) * 100;

            switch ((int) cacaoPercentage) {
                case 30:
                    milkValues.removeFirst();
                    cacaoPowderValues.removeLast();
                    chocolateMade.putIfAbsent("Milk Chocolate", 0);
                    chocolateMade.put("Milk Chocolate", chocolateMade.get("Milk Chocolate") + 1);
                    break;
                case 50:
                    milkValues.removeFirst();
                    cacaoPowderValues.removeLast();
                    chocolateMade.putIfAbsent("Dark Chocolate", 0);
                    chocolateMade.put("Dark Chocolate", chocolateMade.get("Dark Chocolate") + 1);
                    break;
                case 100:
                    milkValues.removeFirst();
                    cacaoPowderValues.removeLast();
                    chocolateMade.putIfAbsent("Baking Chocolate", 0);
                    chocolateMade.put("Baking Chocolate", chocolateMade.get("Baking Chocolate") + 1);
                    break;
                default:
                    milkValues.addLast(milkValues.removeFirst() + 10);
                    cacaoPowderValues.removeLast();
                    break;
            }
        }
        if (chocolateMade.size() == 3){
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }
//        chocolateMade.forEach(Comparator.comparing(chocolateMade::));
        for (Map.Entry<String, Integer> entry : chocolateMade.entrySet()) {

            System.out.printf(" # %s --> %d\n", entry.getKey(), entry.getValue());
        }

    }
}
