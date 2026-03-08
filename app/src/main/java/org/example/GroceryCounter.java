package org.example;

public class GroceryCounter {
    // Counter value (stored as hundredths, e.g., 9999 = 99.99)
    private int counterValue;
    // Maximum counter value before overflow
    private int maxValue;
    // Track how many times the counter wrapped
    private int overflowCount;
    
    public GroceryCounter() {
        this(0, 9999);
    }
    
    public GroceryCounter(int startingValue) {
        this(startingValue, 9999);
    }
    
    public GroceryCounter(int startingValue, int maxValue) {
        if (maxValue <= 0) {
            throw new IllegalArgumentException("Maximum counter value must be a positive integer");
        }
        if (startingValue < 0 || startingValue > maxValue) {
            throw new IllegalArgumentException("Starting value must be between 0 and " + maxValue);
        }
        this.counterValue = startingValue;
        this.maxValue = maxValue;
        this.overflowCount = 0;
    }
    
    // Increment Methods
    public void tens() {
        counterValue += 1000;
        checkOverflow();
    }
    
    public void ones() {
        counterValue += 100;
        checkOverflow();
    }
    
    public void tenths() {
        counterValue += 10;
        checkOverflow();
    }
    
    public void hundredths() {
        counterValue += 1;
        checkOverflow();
    }
    
    // Utility Methods
    public String total() {
        // Divide by 100 to get the whole number part (before decimal)
        int wholePart = counterValue / 100;
        // Use modulo to get the decimal part (after decimal)
        int decimalPart = counterValue % 100;
        // Figure out how many digits we need for the whole part
        int maxWholePart = maxValue / 100;
        int width = 2; // default width
        if (maxWholePart >= 100) {
            width = 3;
        }
        if (maxWholePart >= 1000) {
            width = 4;
        }
        if (maxWholePart >= 10000) {
            width = 5;
        }
        if (maxWholePart >= 100000) {
            width = 6;
        }
        // Build the string with leading zeros
        String wholeStr = "";
        if (width == 2) {
            if (wholePart < 10) {
                wholeStr = "0" + wholePart;
            } 
            else {
                wholeStr = "" + wholePart;
            }
        } 
        else if (width == 3) {
            if (wholePart < 10) {
                wholeStr = "00" + wholePart;
            } 
            else if (wholePart < 100) {
                wholeStr = "0" + wholePart;
            } 
            else {
                wholeStr = "" + wholePart;
            }
        } 
        else if (width == 4) {
            if (wholePart < 10) {
                wholeStr = "000" + wholePart;
            } else if (wholePart < 100) {
                wholeStr = "00" + wholePart;
            } else if (wholePart < 1000) {
                wholeStr = "0" + wholePart;
            } else {
                wholeStr = "" + wholePart;
            }
        } 
        else {
            // For larger widths, just use the number
            wholeStr = "" + wholePart;
        }
        // Add leading zero to decimal part if needed
        String decimalStr = "";
        if (decimalPart < 10) {
            decimalStr = "0" + decimalPart;
        } else {
            decimalStr = "" + decimalPart;
        }
        return wholeStr + "." + decimalStr;
    }
    
    public int overflows() {
        return overflowCount;
    }
    
    public void clear() {
        counterValue = 0;
        overflowCount = 0;
    }
    
    // Private Helper
    private void checkOverflow() {
        if (counterValue > maxValue) {
            counterValue = 0;
            overflowCount++;
        }
    }
}
