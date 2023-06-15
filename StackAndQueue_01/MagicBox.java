import java.util.*;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> firstBox = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(firstBox::offer);

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(secondBox::push);

        int claimedItems = 0;

        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            int currentFirstBox = firstBox.peek();
            int currentSecondBox = secondBox.peek();
            int result = currentFirstBox + currentSecondBox;

            if (result % 2 == 0) {
                firstBox.poll();
                secondBox.pop();
                claimedItems += result;
            } else {
                secondBox.pop();
                firstBox.offer(currentSecondBox);
            }
        }
        if (firstBox.isEmpty()) {
            System.out.println("First magic box is empty.");
        } else {
            System.out.println("Second magic box is empty.");
        }
        if (claimedItems >= 90) {
            System.out.printf("Wow, your prey was epic! Value: %d\n", claimedItems);
        } else {
            System.out.printf("Poor prey... Value: %d\n", claimedItems);
        }
    }
}
