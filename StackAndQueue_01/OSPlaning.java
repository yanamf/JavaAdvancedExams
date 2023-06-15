import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class OSPlaning {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> tasks = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split(",\\s+")).mapToInt(Integer::parseInt).forEach(tasks::push);

        ArrayDeque<Integer> threads = new ArrayDeque<>();
        Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(threads::offer);

        int taskToKill = Integer.parseInt(scan.nextLine());
        boolean taskFinished = false;
        int threadKiller = 0;

        while (!taskFinished) {

            int lastTask = tasks.peek();
            int firstThread = threads.peek();

            if (lastTask == taskToKill) {
                tasks.pop();
                threadKiller = firstThread;
                taskFinished = true;
                break;
            }
            if (firstThread >= lastTask) {
                tasks.pop();
                threads.poll();
            } else {
                threads.poll();
            }
        }
        System.out.printf("Thread with value %d killed task %d\n", threadKiller, taskToKill);
        while (!threads.isEmpty()){
            System.out.print(threads.poll() + " ");
        }
    }
}
