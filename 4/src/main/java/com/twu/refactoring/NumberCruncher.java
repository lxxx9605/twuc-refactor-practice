package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;
    private int evenCount = 0;
    private int oddCount = 0;
    private int positiveCount = 0;
    private int negativeCount = 0;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
        for (int number : numbers) {
            if (number % 2 == 0) evenCount++;
            if (number % 2 == 1) oddCount++;
            if (number >= 0) positiveCount++;
            if (number < 0) negativeCount++;
        }
    }

    public int countEven() {
        return evenCount;
    }

    public int countOdd() {
        return oddCount;
    }

    public int countPositive() {
        return positiveCount;
    }

    public int countNegative() {
        return negativeCount;
    }
}
