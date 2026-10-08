package com.codealpha.flashcardquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.codealpha.flashcardquiz.ui.navigation.AppNavigation
import com.codealpha.flashcardquiz.ui.theme.FlashCardQuizTheme

/**
 * MainActivity — single-activity entry point for the FlashCard Quiz App.
 *
 * Uses Jetpack Compose with edge-to-edge display and the app's Material 3 theme.
 * [AppNavigation] handles the Home ↔ Study screen state.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashCardQuizTheme {
                AppNavigation()
            }
        }
    }
}
