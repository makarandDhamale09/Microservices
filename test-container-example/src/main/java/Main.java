public class Main {

    public static void main(String[] args) {
        int num = 123;
        int numOriginal = num; // Store the original number for later comparison
        int result = 0;

        while (num > 0) {
            int digit = num % 10; // Get the last digit
            result = result * 10 + digit;
            num = num / 10;
        }

        if (result == numOriginal) {
            System.out.println("The number is a palindrome");
        } else {
            System.out.println("The number is not a palindrome");
        }
    }
}