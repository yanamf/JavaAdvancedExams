import java.util.*;

public class AutumnCocktails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        ArrayDeque<Integer> freshnesses = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(ingredients::offer);
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(freshnesses::push);

        Map<String, Integer> cocktails = new TreeMap<>();

        while (!ingredients.isEmpty() && !freshnesses.isEmpty()) {
            int ingredient = ingredients.peek();
            int freshness = freshnesses.peek();
            int result = ingredient * freshness;

            if (ingredient == 0) {
                ingredients.pop();
                continue;
            }
            ingredients.pop();
            freshnesses.poll();
            if (result == 150) {
                cocktails.putIfAbsent("Pear Sour", 0);
                cocktails.put("Pear Sour", cocktails.get("Pear Sour") + 1);
            } else if (result == 250) {
                cocktails.putIfAbsent("The Harvest", 0);
                cocktails.put("The Harvest", cocktails.get("The Harvest") + 1);
            } else if (result == 300) {
                cocktails.putIfAbsent("Apple Hinny", 0);
                cocktails.put("Apple Hinny", cocktails.get("Apple Hinny") + 1);
            } else if (result == 400) {
                cocktails.putIfAbsent("High Fashion", 0);
                cocktails.put("High Fashion", cocktails.get("High Fashion") + 1);
            } else {
                ingredients.offer(ingredient + 5);
            }
        }
        if (cocktails.size() >= 4) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }
        if (!ingredients.isEmpty()) {
            int sumIngredients = 0;
            while (!ingredients.isEmpty()) {
                sumIngredients += ingredients.poll();
            }
            System.out.printf("Ingredients left: %d\n", sumIngredients);
        }

        for (Map.Entry<String, Integer> entry : cocktails.entrySet()) {
            System.out.printf(" # %s --> %s\n", entry.getKey(), entry.getValue());
        }
    }
}



