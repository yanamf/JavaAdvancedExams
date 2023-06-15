import java.util.*;
import java.util.stream.Collectors;

public class Cooking {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(liquids::offer);

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(ingredients::push);

        Map<String, Integer> food = new TreeMap<>();
        while (!liquids.isEmpty() && !ingredients.isEmpty()){
            int currentLiquid = liquids.peek();
            int currentIngredient = ingredients.peek();
            int result = currentLiquid + currentIngredient;
            liquids.poll();
            ingredients.pop();
            if (result == 25){
                //bread
                food.putIfAbsent("Bread", 0);
                food.put("Bread", food.get("Bread") + 1);
            } else if (result == 50) {
                food.putIfAbsent("Cake", 0);
                food.put("Cake", food.get("Cake") + 1);
            } else if (result == 75) {
                food.putIfAbsent("Pastry", 0);
                food.put("Pastry", food.get("Pastry") + 1);
            } else if (result == 100) {
                food.putIfAbsent("Fruit Pie", 0);
                food.put("Fruit Pie", food.get("Fruit Pie") + 1);
            } else {
                ingredients.push(currentIngredient + 3);
            }
        }
        if (food.size() == 4){
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }
        if (liquids.isEmpty()){
            System.out.println("Liquids left: none");
        } else {
            System.out.print("Liquids left: ");
            System.out.print(String.join(", ", liquids.stream().map(String::valueOf).collect(Collectors.toList())));
            System.out.println();
        }
        if (ingredients.isEmpty()){
            System.out.println("Ingredients left: none");
        } else {
            System.out.print("Ingredients left: ");
            System.out.print(String.join(", ", ingredients.stream().map(String::valueOf).collect(Collectors.toList())));
            System.out.println();
        }
        food.putIfAbsent("Bread", 0);
        food.putIfAbsent("Cake", 0);
        food.putIfAbsent("Pastry", 0);
        food.putIfAbsent("Fruit Pie", 0);
        for (Map.Entry<String, Integer> entry : food.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

    }
}
