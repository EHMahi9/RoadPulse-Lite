package util;

import java.util.Scanner;

public class InputHelper {

    public static final Scanner scanner = new Scanner(System.in);

    private InputHelper() {

    }

    /**
     * Read a non-blank line of text from the user.
     */
    public static String readString(String prompt) {

        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        while (input.isEmpty()) {

            System.out.print(prompt);
            input = scanner.nextLine().trim();

        }

        return input;

    }

    /**
     * Safely read an integer from the user without crashing on non-numeric input.
     */
    public static int readInt(String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("\nInvalid input. Please enter a valid number.");

            }

        }

    }

    /**
     * Read an integer within a specified range [min, max].
     */
    public static int readInt(String prompt, int min, int max) {

        while (true) {

            int value = readInt(prompt);

            if (value >= min && value <= max) {

                return value;

            }

            System.out.println("\nPlease enter a number between " + min + " and " + max + ".");

        }

    }

    /**
     * Wait for the user to press Enter before continuing.
     */
    public static void pressEnterToContinue() {

        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();

    }

}
