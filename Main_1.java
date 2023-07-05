import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int[] prefixFunction(String string) {
        int[] prefixArray = new int[string.length()];
        for (int i = 1; i < string.length(); i++) {
            int referencePosition = prefixArray[i - 1];
            while (referencePosition > 0 && string.charAt(i) != string.charAt(referencePosition)) {
                referencePosition = prefixArray[referencePosition - 1];
            }
            if (string.charAt(i) == string.charAt(referencePosition)) {
                prefixArray[i] = referencePosition + 1;
            } else {
                prefixArray[i] = referencePosition;
            }
        }

        return prefixArray;
    }


    public static void KMP(String pattern, String text) {
        int[] prefixArray = prefixFunction(pattern + '#' + text);
        int lengthPattern = pattern.length();
        int numRes = 0;
        for (int i = 0; i < text.length(); i++) {
            if (prefixArray[lengthPattern + 1 + i] == lengthPattern) {
                if (numRes != 0) {
                    System.out.print(",");
                }
                System.out.print(i - lengthPattern + 1);
                numRes++;
            }
        }
        if (numRes == 0) {
            System.out.println("-1");
        }
    }

    public static void cyclicShift(String stringA, String stringB) {
        if (stringB.length() == stringA.length()) {
            int [] prefixArray = prefixFunction(stringB + '#' + stringA + stringA);
            int lengthB = stringB.length();
            for (int i = 0; i < stringA.length() * 2; i++) {
                if (prefixArray[lengthB + 1 + i] == lengthB) {
                    System.out.println(i - lengthB + 1);
                    System.exit(0);
                }
            }
        }
        System.out.println("-1");
    }

        public static void main (String[]args){
            Scanner scanner = new Scanner(System.in);
            String pattern = scanner.next();
            String text = scanner.next();
            KMP(pattern, text);
            cyclicShift(pattern, text);
        }
    }