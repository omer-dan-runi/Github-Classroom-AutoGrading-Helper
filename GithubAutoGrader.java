import java.util.Scanner;

public class GithubAutoGrader
{
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! please enter your error text here:");
        String errorText = scanner.nextLine();
        System.out.println();

        // I wanted to create a tester that also works on HW6 code (which check 'included' and not equal),
        // but the terminal has  an input limit which makes it useless (HW6 input is more that 100k characters long)
        // Im sure there are many ways to pass that, such as importing the github logs file and checking that
        // but I am too busy to do that, IF you want to do that, feel free to and create a pull request,
        // I will review your code and if it is working properly Ill merge it into the main code and by that everyone
        // who uses this helper can enjot your help.
        // I also left some of my unfinished code that you can use, this code is not tested and treat it like that,
        // I am not a java developer, so syntax could be wrong.
        // of course, you will get full credit on your part, which will make you 0% richer, but maybe 1% happier :)
        // Contact info: Omer Dan - 0526618546


        // System.out.println("Please enter the HW number (for HW6 enter '6')");
        // int hwNum = scanner.nextInt();
        // System.out.println();

        if (errorText == null || errorText.isEmpty())
        {
            System.out.println("No text detected");
            return;
        }
        else
        {
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

            // findDifference(expectedString, actualString, hwNum == 6);
            findDifference(expectedString, actualString, false);

        }
        
        scanner.nextLine();
    }

    static void findDifference(String expected, String actual, boolean isInclude)
    {
        String[] expectedSentences = expected.split("%0A");
        String[] actualSentences = actual.split("%0A");

        for (int i = 0; i < Math.min(expectedSentences.length, actualSentences.length); i++)
        {
            String[] expectedWords = expectedSentences[i].split(" ");
            String[] actualWords = actualSentences[i].split(" ");

            // if (!isInclude)
            // {
                for (int j = 0; j < Math.min(expectedWords.length, actualWords.length); j++)
                {
                    if (!expectedWords[j].equals(actualWords[j]))
                    {
                        System.out.println("Expected   - " + expectedSentences[i] +
                                "\nActual     - " + actualSentences[i] +
                                "\nDifference - '" + actualWords[j] + "' should have been '" + expectedWords[j] + "'");
    
                        System.out.println();
                    }
                }
            // }
            // else 
            // {
            //     int expectedIndex = 0;
        
            //     for (String word : actualWords)
            //     {
            //         if (expectedIndex < expectedWords.length && word.equals(expectedWords[expectedIndex]))
            //         {
            //             expectedIndex++;
            //         }
            //         else if (expectedIndex == expectedWords.length)
            //         {
            //             System.out.println("If you see this, congratulations! I have no idea what is the problem of your output \n"+
            //             "Feel free to send me a message on 052-6618546 so I can check this problem and make this helper better for all of us"); 
            //         }
            //         else
            //         {
            //             System.out.println("Your output needs to have this sequence:");
            //             for (String expectedword : expectedWords)
            //             {
            //                 System.out.print(expectedword + " ");
            //             }
            //             System.out.println();
            //             System.out.println("This sequence is not in your output at that order at all");
            //             System.out.println("They normally check the last line of your output, focus on that and when"+
            //             " you run your program locally, look for the expected line at the end of your output.");
            //             System.out.println("If you dont know how to run this test locally, I can help you :)");
            //             System.out.println("Omer Dan - 052-6618546");
            //         }
            //     }
            // }
            System.out.println("- - - - - - - - - - - - -- - - - - - - ");
        }
    }
}
