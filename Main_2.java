import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Integer> prefixFunction(String string) {
        List<Integer> prefixArray = new ArrayList<>(string.length());
        for (int i = 0; i < string.length(); i++) {
            prefixArray.add(0);
        }

        for (int i = 1; i < string.length(); i++) {
            int referencePosition = prefixArray.get(i - 1);

            while (referencePosition > 0 && string.charAt(i) != string.charAt(referencePosition)) {
                referencePosition = prefixArray.get(referencePosition - 1);
            }

            if (string.charAt(i) == string.charAt(referencePosition)) {
                prefixArray.set(i, referencePosition + 1);
            } else {
                prefixArray.set(i, referencePosition);
            }
        }

        return prefixArray;
    }

    public static void print(List<Integer> arrayEntryPosition) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayEntryPosition.size(); i++) {
            sb.append(arrayEntryPosition.get(i));
            if (i != arrayEntryPosition.size() - 1)
                sb.append(',');
        }
        if (arrayEntryPosition.isEmpty()) {
            sb.append("-1");
        }
        System.out.println(sb.toString());
    }

    public static void KMP(String pattern, String text) {
        List<Integer> prefixArray = prefixFunction(pattern + '#' + text);
        int patternLength = pattern.length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (prefixArray.get(patternLength + 1 + i) == patternLength) {
                result.add(i - patternLength + 1);
            }
        }
        print(result);
    }

    public static void cyclicShift(String stringA, String stringB) {
        if (stringB.length() == stringA.length()) {
            List<Integer> prefixArray = prefixFunction(stringB + '#' + stringA + stringA);
            int lengthB = stringB.length();
            for (int i = 0; i < stringA.length() * 2; i++) {
                if (prefixArray.get(lengthB + 1 + i) == lengthB) {
                    System.out.println(i - lengthB + 1);
                }
            }
        }
        else
            System.out.println("-1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.next();
        String text = scanner.next();
        KMP(pattern, text);
       // cyclicShift(pattern, text);
    }
}