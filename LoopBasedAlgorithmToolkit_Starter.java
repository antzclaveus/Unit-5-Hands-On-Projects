import java.util.Scanner;

/**
 * Unit 5 - Loop-Based Algorithm Toolkit
 * Implements five algorithmic tools using all three loop types (for, while, do-while),
 * break/continue statements, and detailed comments explaining loop logic.
 */
public class LoopBasedAlgorithmToolkit_Starter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ============================================================
        // 1) Generate the FIRST 100 PRIME NUMBERS
        // Loop choice: while loop to keep generating candidates until we have 100 primes;
        //              inner for loop to test divisors up to sqrt(n).
        // Exit condition: outer loop exits when primeCount reaches 100.
        // Efficiency: testing divisors only up to sqrt(n) reduces checks from O(n) to O(√n).
        //             break exits the inner loop immediately when a factor is found.
        // ============================================================

        System.out.println("=== First 100 Prime Numbers ===");

        int primeCount = 0;   // tracks how many primes we've found
        int candidate = 2;    // start checking from 2, the smallest prime

        // Outer while loop: continue until we've found 100 primes
        while (primeCount < 100) {
            boolean isPrime = true;

            // Inner for loop: test divisors from 2 up to sqrt(candidate)
            // Using i * i <= candidate avoids costly Math.sqrt() call
            for (int i = 2; i * i <= candidate; i++) {
                if (candidate % i == 0) {
                    isPrime = false;
                    break; // a factor was found; no need to check further divisors
                }
            }

            if (isPrime) {
                System.out.print(candidate);
                primeCount++;
                // Print a newline every 10 primes for readability
                if (primeCount % 10 == 0) {
                    System.out.println();
                } else {
                    System.out.print("  ");
                }
            }
            candidate++; // move to the next candidate
        }

        System.out.println(); // spacing

        // ============================================================
        // 2) Compute the GCD of TWO USER INPUTS (Euclidean Algorithm)
        // Loop choice: while loop — we don't know in advance how many iterations are needed;
        //              we loop until b becomes 0.
        // Exit condition: loop exits when b == 0; at that point a holds the GCD.
        // Efficiency: Euclidean algorithm is O(log(min(a,b))), far better than brute force.
        // ============================================================

        System.out.println("=== GCD Calculator ===");
        System.out.print("Enter first integer (a): ");
        int a = scanner.nextInt();
        System.out.print("Enter second integer (b): ");
        int b = scanner.nextInt();

        // Use absolute values to handle negative inputs gracefully
        a = Math.abs(a);
        b = Math.abs(b);

        // Euclidean algorithm: repeatedly replace (a, b) with (b, a % b)
        while (b != 0) {
            int temp = b;
            b = a % b; // remainder becomes the new b
            a = temp;  // old b becomes the new a
        }
        // When loop exits, a holds the GCD
        System.out.println("GCD = " + a);

        // ============================================================
        // 3) Convert DECIMAL to HEXADECIMAL
        // Loop choice: do-while — guarantees the body runs at least once,
        //              which correctly handles the edge case where the input is 0.
        // Exit condition: loop exits when the number becomes 0 after division.
        // Efficiency: each iteration reduces the number by a factor of 16 → O(log₁₆ n).
        // ============================================================

        System.out.println("=== Decimal to Hexadecimal Converter ===");
        System.out.print("Enter a non-negative integer: ");
        int decimal = scanner.nextInt();

        String digits = "0123456789ABCDEF";
        StringBuilder hexResult = new StringBuilder();
        int num = decimal;

        // do-while ensures at least one digit is produced (handles input of 0)
        do {
            int remainder = num % 16;            // get the least-significant hex digit
            hexResult.insert(0, digits.charAt(remainder)); // prepend digit to build string right-to-left
            num = num / 16;                      // shift right by one hex digit
        } while (num > 0); // exit once all digits have been extracted

        System.out.println(decimal + " in hexadecimal is: " + hexResult);

        // ============================================================
        // 4) Palindrome Check (STRING)
        // Loop choice: for loop — we know exactly how many character pairs to compare
        //              (half the string length), making a for loop the natural fit.
        // Exit condition: loop runs up to length/2 iterations, or exits early via break
        //                 when a mismatch is found.
        // Efficiency: compares at most n/2 characters; break avoids unnecessary comparisons.
        // ============================================================

        System.out.println("=== Palindrome Checker ===");
        System.out.print("Enter a string to check: ");
        String input = scanner.next().toLowerCase(); // case-insensitive comparison

        boolean isPalindrome = true;
        int len = input.length();

        // Compare characters from both ends moving inward
        for (int i = 0; i < len / 2; i++) {
            if (input.charAt(i) != input.charAt(len - 1 - i)) {
                isPalindrome = false;
                break; // mismatch found; no need to continue checking
            }
        }

        if (isPalindrome) {
            System.out.println("\"" + input + "\" is a palindrome: true");
        } else {
            System.out.println("\"" + input + "\" is a palindrome: false");
        }

        // ============================================================
        // 5) Nested loops: MULTIPLICATION TABLE
        // Loop choice: nested for loops — both row and column counts are fixed (1–10),
        //              so for loops with known bounds are the clearest choice.
        // Exit condition: outer loop exits after 10 rows; inner loop exits after 10 columns.
        // Efficiency: O(n²) is unavoidable for printing an n×n table; nested loops reflect this directly.
        // ============================================================

        System.out.println("=== 10x10 Multiplication Table ===");

        // Print column header
        System.out.print("    ");
        for (int col = 1; col <= 10; col++) {
            System.out.printf("%4d", col);
        }
        System.out.println();
        System.out.println("    " + "----".repeat(10));

        // Outer loop iterates over each row (multiplier 1–10)
        for (int row = 1; row <= 10; row++) {
            System.out.printf("%2d |", row); // row label

            // Inner loop iterates over each column (multiplicand 1–10)
            for (int col = 1; col <= 10; col++) {
                System.out.printf("%4d", row * col); // right-align each product in 4 chars
            }
            System.out.println(); // newline after each row
        }

        scanner.close();
    }
}
