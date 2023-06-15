import java.util.*;

public class Bouquets {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayDeque<Integer> tulips = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split(",\\s+")).mapToInt(Integer::parseInt).forEach(tulips::push);
        ArrayDeque<Integer> daffodils = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split(",\\s+")).mapToInt(Integer::parseInt).forEach(daffodils::offer);
        List<Integer> flowersLeft = new ArrayList<>();
        int bouquetsCount = 0;
        while (!tulips.isEmpty() && !daffodils.isEmpty()) {
            int tulip = tulips.peek();
            int daffodil = daffodils.peek();
            int result = tulip + daffodil;
            tulips.pop();
            if (result == 15) {
                bouquetsCount++;
                daffodils.poll();
            } else if (result > 15) {
                tulips.push(tulip - 2);
            } else {
                //store for later
                flowersLeft.add(result);
                daffodils.poll();
            }
        }
        if (!flowersLeft.isEmpty()) {
            int sum = flowersLeft.stream().mapToInt(Integer::intValue).sum();
            int countNewFlowers = sum / 15;
            bouquetsCount += countNewFlowers;
        }
        if (bouquetsCount >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!\n", bouquetsCount);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - bouquetsCount);
        }
    }


}
