import java.util.Scanner;

public class GuessNumber {

    public static void main(String[] args) {
        // Generate a random number
        //int secretNumber = (int) (Math.random() * 100) + 1;
    	int secretNumber = 25;
        // Declare variables
  	    int guess=0;
        int attempts = 0;
        int maxAttempts = 3;
        int score = 0;

        // Start the game
        System.out.println("****************** Welcome to the Game of Guess! *********************\n");
        System.out.println("I have generated a random number between 1 and 100!\n");
        System.out.println("You have " +maxAttempts+ " Attempts!\n");
        System.out.println("Can you guess it?\n");

        // Loop until the user guesses the number correctly or runs out of attempts
        while (guess != secretNumber && attempts < maxAttempts) {
            // Get the user's guess
            System.out.print("Enter your guess: ");
            Scanner scanner = new Scanner(System.in);
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid guess. Please enter a number.\n");
                continue;
            }
            //System.out.println("You have one more attempt...Can you guess it!\n");

            // Increment the number of attempts
            attempts++;
            if (guess < 1 || guess > 100) {
                System.out.println("Invalid guess. Please enter a number between 1 and 100.\n");
            } else {
            // Check if the guess is correct
            if (guess == secretNumber) {
                score = 100 - attempts;
                System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts!\n");
                break;
            } else if (guess < secretNumber) {
                System.out.println("Your guess is too low.\n");
            } else {
                System.out.println("Your guess is too high.\n");
            }
        }
        }

        // If the user runs out of attempts, display the correct number
        if (guess != secretNumber) {
            System.out.println("Sorry, you ran out of attempts. The correct number was " + secretNumber + ".\n");
        }

        // Display the score
        System.out.println("Your score is " + score + ".");
    }
}