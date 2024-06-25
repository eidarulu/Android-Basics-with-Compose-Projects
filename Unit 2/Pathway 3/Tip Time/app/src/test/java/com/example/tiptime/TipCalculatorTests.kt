package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        // Create two variables: amount set to 10.00 and tipPercent set to 20.00
        val amount = 10.00
        val tipPercent = 20.00

        // Format the expected tip amount as in the app code
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        // Call the calculateTip() method, passing amount, tipPercent, and false for the roundup flag
        val actualTip = calculateTip(amount, tipPercent, false)

        // Check if the returned tip amount is equal to the expected value
        assertEquals(expectedTip, actualTip)
    }
}