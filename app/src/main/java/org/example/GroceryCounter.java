package org.example;

public class GroceryCounter {
    // Individual digits that hold tens, ones, tenths, and hundredths
    private int tensDigit;
    private int onesDigit;
    private int tenthsDigit;
    private int hundredthsDigit;
    // Track how many times the counter wrapped from 9999 → 0000
    private int overflowCount;
    public GroceryCounter() {
        tensDigit = 0;
        onesDigit = 0;
        tenthsDigit = 0;
        hundredthsDigit = 0;
        overflowCount = 0;
    }
    // Increment Methods
    public void tens() {
        tensDigit++;
        if (tensDigit == 10) {
            tensDigit = 0;
            handleOverflow();
        }
    }
    public void ones() {
        onesDigit++;
        if (onesDigit == 10) {
            onesDigit = 0;
            tens();
        }
    }
    public void tenths() {
        tenthsDigit++;
        if (tenthsDigit == 10) {
            tenthsDigit = 0;
            ones();
        }
    }
    public void hundredths() {
        hundredthsDigit++;
        if (hundredthsDigit == 10) {
            hundredthsDigit = 0;
            tenths();
        }
    }
    // Utility Methods
    public String total() {
        return "" + tensDigit + onesDigit + "." + tenthsDigit + hundredthsDigit;
    }
    public int overflows() {
        return overflowCount;
    }
    public void clear() {
        tensDigit = 0;
        onesDigit = 0;
        tenthsDigit = 0;
        hundredthsDigit = 0;
        overflowCount = 0;
    }
    // Private Helper
    private void handleOverflow() {
        overflowCount++;
    }
}
