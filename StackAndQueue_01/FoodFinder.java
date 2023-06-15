import java.util.*;
import java.util.stream.Collectors;

public class FoodFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Character> vowels = Arrays.stream(scanner.nextLine().split(" "))
                .map(s -> s.charAt(0))
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Character> consonants = Arrays.stream(scanner.nextLine().split(" "))
                .map(s -> s.charAt(0))
                .collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Character> pear = Arrays.stream(new Character[]{'p', 'e', 'a', 'r'})
                .collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Character> flour = Arrays.stream(new Character[]{'f', 'l', 'o', 'u', 'r'})
                .collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Character> pork = Arrays.stream(new Character[]{'p', 'o', 'r', 'k'})
                .collect(Collectors.toCollection(ArrayDeque::new));
        ArrayDeque<Character> olive = Arrays.stream(new Character[]{'o', 'l', 'i', 'v', 'e'})
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (!consonants.isEmpty()) {
            char currentVowel = vowels.removeFirst();
            vowels.addLast(currentVowel);
            char currentConsonant = consonants.removeLast();
            if (pear.contains(currentVowel)) {
                pear.remove(currentVowel);
            }
            if (pear.contains(currentConsonant)) {
                pear.remove(currentConsonant);
            }
            if (flour.contains(currentVowel)) {
                flour.remove(currentVowel);
            }
            if (flour.contains(currentConsonant)) {
                flour.remove(currentConsonant);
            }
            if (pork.contains(currentVowel)) {
                pork.remove(currentVowel);
            }
            if (pork.contains(currentConsonant)) {
                pork.remove(currentConsonant);
            }
            if (olive.contains(currentVowel)) {
                olive.remove(currentVowel);
            }
            if (olive.contains(currentConsonant)) {
                olive.remove(currentConsonant);
            }
        }
        int wordsCount = 0;
        List<String> words = new ArrayList<>();
        if (pear.isEmpty()){
            wordsCount++;
            words.add("pear");
        }
        if (flour.isEmpty()){
            wordsCount++;
            words.add("flour");
        }
        if (pork.isEmpty()){
            wordsCount++;
            words.add("pork");
        }
        if (olive.isEmpty()){
            wordsCount++;
            words.add("olive");
        }
        System.out.printf("Words found: %d\n", wordsCount);
        System.out.print(String.join("\n", words));

    }
}
