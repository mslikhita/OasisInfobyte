import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + maxAttempts + " attempts to guess the number between " + minRange + " and " + maxRange + ".");

        while (true) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); 

                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    score += (maxAttempts - attempts + 1);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Try a higher number. Attempts left: " + (maxAttempts - attempts));
                } else {
                    System.out.println("Try a lower number. Attempts left: " + (maxAttempts - attempts));
                }

                if (attempts == maxAttempts) {
                    System.out.println("Out of attempts. The correct number was: " + targetNumber);
                }
            }

            System.out.println("Your current score: " + score);

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Your final score: " + score);
                break;
            }
        }

        scanner.close();
    }
}

