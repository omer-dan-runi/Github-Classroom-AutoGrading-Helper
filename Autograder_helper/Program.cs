class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Hi! please enter your error text here:");
        string? errorText = Console.ReadLine();
        Console.WriteLine();

        if (errorText is null)
        {
            Console.WriteLine("No text detected");
            return;
        }
        else 
        {
            Console.WriteLine("--------------------------------------------");


            // Extract "method and input" string
            int startIndex = errorText.IndexOf("The output for test") + "The output for test".Length;
            int endIndex = errorText.IndexOf("did not match");
            string methodAndInput = errorText.Substring(startIndex, endIndex - startIndex).Trim();

            // Extract "expectedString" string
            startIndex = errorText.IndexOf("Expected:%0") + "Expected:%0A".Length;
            endIndex = errorText.IndexOf("%0AActual:%0A");
            string expectedString = errorText.Substring(startIndex, endIndex - startIndex).Trim();

            // Extract "actualString" string
            startIndex = errorText.IndexOf("%0AActual:%0A") + "%0AActual:%0A".Length;
            string actualString = errorText.Substring(startIndex).Trim();

            //FindDifference(expectedString, actualString);
            FindDifference("Loan sum = 100000.0, interest rate = 3.0%25, periods = 12", "Loan532 sum = 100000.0, interest rate = 3.0%25, periods = 12");
            Console.ReadLine();


        }

        Console.ReadLine();
    }

    static void FindDifference(string expected, string actual)
    {
        var expectedSentences = expected.Split(new[] { "%0A" }, StringSplitOptions.RemoveEmptyEntries);
        var actualSentences = actual.Split(new[] { "%0A" }, StringSplitOptions.RemoveEmptyEntries);

        for (int i = 0; i < Math.Min(expectedSentences.Length, actualSentences.Length); i++)
        {
            var expectedWords = expectedSentences[i].Split(' ');
            var actualWords = actualSentences[i].Split(' ');

            for (int j = 0; j < Math.Min(expectedWords.Length, actualWords.Length); j++)
            {
                if (expectedWords[j] != actualWords[j])
                {
                    Console.WriteLine($"Expected - {expectedSentences[i]}\n" +
                                      $"Actual - {actualSentences[i]}\n" +
                                      $"Difference - '{actualWords[j]}' should have been '{expectedWords[j]}'");

                    Console.WriteLine();
                }
            }

            Console.WriteLine("- - - - - - - - - - - - -- - - - - - - ");
        }
    }
}