package StackAndQueue_01;

import java.util.*;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> lilies = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split(",\\s+")).mapToInt(Integer::parseInt).forEach(lilies::push);
        ArrayDeque<Integer> roses = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split(",\\s+")).mapToInt(Integer::parseInt).forEach(roses::offer);
        int wreathCount = 0;
        int flowersLeft = 0;

        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int lily = lilies.peek();
            int rose = roses.peek();
            int result = lily + rose;
            lilies.poll();

            if (result == 15) {
                wreathCount++;
                roses.pop();
            } else if (result > 15) {
                lilies.push(lily - 2);
            } else {
                //store for later
                flowersLeft += result;
                roses.pop();
            }
        }
        if (flowersLeft != 0) {
            int countNewFlowers = flowersLeft / 15;
            wreathCount += countNewFlowers;
        }
        if (wreathCount >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!\n", wreathCount);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!", 5 - wreathCount);
        }
    }
}
