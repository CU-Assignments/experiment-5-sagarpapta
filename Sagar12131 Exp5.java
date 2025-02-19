/**
Write a Java program to calculate the sum of a list of integers using autoboxing and unboxing. Include methods to parse strings into their respective wrapper classes (e.g., Integer.parseInt()).
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumOfIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of integers you want to input:");
        int count = Integer.parseInt(scanner.nextLine());

        String[] numberStrings = new String[count];
        System.out.println("Enter the integers:");

        for (int i = 0; i < count; i++) {
            numberStrings[i] = scanner.nextLine();
        }

        List<Integer> integerList = parseStringsToIntegers(numberStrings);
        int sum = calculateSum(integerList);
        System.out.println("The sum of the list of integers is: " + sum);
        
        scanner.close();
    }

    private static List<Integer> parseStringsToIntegers(String[] numberStrings) {
        List<Integer> integerList = new ArrayList<>();
        for (String numberString : numberStrings) {
            integerList.add(Integer.parseInt(numberString));
        }
        return integerList;
    }

    private static int calculateSum(List<Integer> integerList) {
        int sum = 0;
        for (Integer number : integerList) {
            sum += number;
        }
        return sum;
    }
}
