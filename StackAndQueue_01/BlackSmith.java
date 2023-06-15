import java.util.*;
import java.util.stream.Collectors;

public class BlackSmith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> steel = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Integer> carbon = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        Map<String, Integer> swords = new TreeMap<>();

        while (!steel.isEmpty() && !carbon.isEmpty()) {
            int result = steel.getFirst() + carbon.getLast();
            int currentSteel = steel.removeFirst();
            int currentCarbon = carbon.removeLast();
            switch (result) {
                case 70:
                    swords.putIfAbsent("Gladius", 0);
                    swords.put("Gladius", swords.get("Gladius") + 1);
                    break;
                case 80:
                    swords.putIfAbsent("Shamshir", 0);
                    swords.put("Shamshir", swords.get("Shamshir") + 1);
                    break;
                case 90:
                    swords.putIfAbsent("Katana", 0);
                    swords.put("Katana", swords.get("Katana") + 1);
                    break;
                case 110:
                    swords.putIfAbsent("Sabre", 0);
                    swords.put("Sabre", swords.get("Sabre") + 1);
                    break;
                default:
                    carbon.addLast(currentCarbon + 5);
                    break;
            }
        }
        if (swords.size() >= 1) {
//            int sum = entry.getValue().values().stream().mapToInt(i -> i).sum();        }
            int quantitySwords = 0;
            for (Map.Entry<String, Integer> entry : swords.entrySet()) {
                int currentValue = entry.getValue();
                quantitySwords += currentValue;
            }
            System.out.printf("You have forged %d swords.\n", quantitySwords);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }
        if (steel.isEmpty()){
            System.out.println("Steel left: none");
        } else {
            System.out.print("Steel left: ");
            for (int i = 0; i < steel.size(); i++) {
                if (steel.size() == 1){
                    System.out.print(steel.remove());
                } else {
                    System.out.print(steel.removeLast() + ", ");
                }
                i--;
            }
            System.out.println();
        }
        if (carbon.isEmpty()){
            System.out.println("Carbon left: none\n");
        } else {
            System.out.print("Carbon left: ");
            for (int i = 0; i < carbon.size(); i++) {
                if (carbon.size() == 1){
                    System.out.print(carbon.remove());
                } else {
                    System.out.print(carbon.removeLast() + ", ");
                }
                i--;
                }
            System.out.println();
            }

        for (Map.Entry<String, Integer> entry1 : swords.entrySet()) {
            System.out.printf("%s: %d\n", entry1.getKey(), entry1.getValue());
        }

    }
}
