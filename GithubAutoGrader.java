import java.util.Scanner;

public class GithubAutoGrader {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! please enter your error text here:");
        String errorText = scanner.nextLine();
        System.out.println();

        if (errorText == null || errorText.isEmpty()) {
            System.out.println("No text detected");
            return;
        } else {
            System.out.println("--------------------------------------------");

            // Extract "method and input" string
            int startIndex = errorText.indexOf("The output for test") + "The output for test".length();
            int endIndex = errorText.indexOf("did not match");
            String methodAndInput = errorText.substring(startIndex, endIndex).trim();

            // Extract "expectedString" string
            startIndex = errorText.indexOf("Expected:%0") + "Expected:%0A".length();
            endIndex = errorText.indexOf("%0AActual:%0A");
            String expectedString = errorText.substring(startIndex, endIndex).trim();

            // Extract "actualString" string
            startIndex = errorText.indexOf("%0AActual:%0A") + "%0AActual:%0A".length();
            String actualString = errorText.substring(startIndex).trim();

            findDifference(expectedString, actualString);
        }
        
        scanner.nextLine();
    }

    static void findDifference(String expected, String actual) {
        String[] expectedSentences = expected.split("%0A");
        String[] actualSentences = actual.split("%0A");

        for (int i = 0; i < Math.min(expectedSentences.length, actualSentences.length); i++) {
            String[] expectedWords = expectedSentences[i].split(" ");
            String[] actualWords = actualSentences[i].split(" ");

            for (int j = 0; j < Math.min(expectedWords.length, actualWords.length); j++) {
                if (!expectedWords[j].equals(actualWords[j])) {
                    System.out.println("Expected - " + expectedSentences[i] +
                            "\nActual - " + actualSentences[i] +
                            "\nDifference - '" + actualWords[j] + "' should have been '" + expectedWords[j] + "'");

                    System.out.println();
                }
            }

            System.out.println("- - - - - - - - - - - - -- - - - - - - ");
        }
    }
}
