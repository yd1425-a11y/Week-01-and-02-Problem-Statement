package main.java.problems;

public class Main {

    public static void main(String[] args) {

        UsernameAvailabilityChecker checker =
                new UsernameAvailabilityChecker();

        checker.registerUsername("john_doe", 101);

        System.out.println(
                checker.checkAvailability("john_doe")
        );

        System.out.println(
                checker.checkAvailability("jane_smith")
        );

        System.out.println(
                checker.suggestAlternatives("john_doe")
        );

        System.out.println(
                checker.getMostAttempted()
        );

    }
}