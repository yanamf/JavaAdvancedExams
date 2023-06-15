import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Meeting2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> maleStack = new ArrayDeque<>();
        ArrayDeque<Integer> femaleQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(maleStack::push);
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(femaleQueue::offer);

        int matches = 0;
        while (!maleStack.isEmpty() && !femaleQueue.isEmpty()) {
            int currentMale = maleStack.peek();
            int currentFemale = femaleQueue.peek();

            if (currentMale <= 0) {
                maleStack.poll();
                continue;
            }
            if (currentFemale <= 0) {
                femaleQueue.pop();
                continue;
            }
            if (currentMale % 25 == 0) {
                maleStack.poll();
                if (maleStack.isEmpty()){
                    break;
                }
                maleStack.poll();
                continue;
            }
            if (currentFemale % 25 == 0) {
                femaleQueue.pop();
                if (femaleQueue.isEmpty()){
                    break;
                }
                femaleQueue.pop();
                continue;
            }

            if (currentMale == currentFemale) {
                matches++;
                maleStack.poll();
                femaleQueue.pop();
            } else {
                femaleQueue.pop();
                int maleToAdd = maleStack.poll() - 2;
                maleStack.push(maleToAdd);
            }
        }
        System.out.printf("Matches: %d\n", matches);

        if (maleStack.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: ");
            System.out.println(String.join(", ", maleStack.stream().map(String::valueOf).collect(Collectors.toList())));
        }
        if (femaleQueue.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: ");
            System.out.println(String.join(", ", femaleQueue.stream().map(String::valueOf).collect(Collectors.toList())));
        }
    }

}
