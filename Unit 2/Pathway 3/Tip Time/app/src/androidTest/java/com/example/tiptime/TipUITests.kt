package com.example.tiptime

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    // Rule to set up the Compose test environment
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        // Set the content of the Compose UI for testing
        composeTestRule.setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TipTimeLayout()
                }
            }
        }

        // Find the node (UI element) with text "Bill Amount" and enter the value "10"
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")

        // Find the node with text "Tip Percentage" and enter the value "20"
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")

        // Format the expected tip amount as currency
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        // Find the node with the expected tip amount text and assert it exists
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}