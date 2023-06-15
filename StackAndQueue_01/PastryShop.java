import java.util.*;
import java.util.stream.Collectors;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(liquids::offer);
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(ingredients::push);

        Map<String, Integer> food = new HashMap<>();
        while (!liquids.isEmpty() && !ingredients.isEmpty()){
            int currentLiquid = liquids.peek();
            int currentIngredient = ingredients.peek();

            int result = currentLiquid + currentIngredient;

            liquids.pop();
            ingredients.poll();

            if (result == 25){
                food.putIfAbsent("Biscuit", 0);
                food.put("Biscuit", food.get("Biscuit") + 1);
            } else if (result == 50) {
                food.putIfAbsent("Cake", 0);
                food.put("Cake", food.get("Cake") + 1);
            } else if (result == 75) {
                food.putIfAbsent("Pastry", 0);
                food.put("Pastry", food.get("Pastry") + 1);
            } else if (result == 100) {
                food.putIfAbsent("Pie", 0);
                food.put("Pie", food.get("Pie") + 1);
            } else {
                ingredients.push(currentIngredient + 3);
            }
        }
        if (food.size() == 4){
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }
        if (liquids.isEmpty()){
            System.out.println("Liquids left: none");
        } else {
            System.out.print("Liquids left: ");
            System.out.println(String.join(", ", liquids.stream().map(String::valueOf).collect(Collectors.toList())));
        }
        if (ingredients.isEmpty()){
            System.out.println("Ingredients left: none");
        } else {
            System.out.print("Ingredients left: ");
            System.out.println(String.join(", ", ingredients.stream().map(String::valueOf).collect(Collectors.toList())));
        }
        String[] order = {"Biscuit", "Cake", "Pie", "Pastry"};

        for (String item : order) {
            System.out.printf("%s: %d%n", item, food.getOrDefault(item, 0));
        }


    }
}
