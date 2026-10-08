package com.codealpha.flashcardquiz.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.codealpha.flashcardquiz.data.model.Deck
import com.codealpha.flashcardquiz.ui.screen.HomeScreen
import com.codealpha.flashcardquiz.ui.screen.StudyScreen

/**
 * Sealed hierarchy representing every screen in the app.
 *
 * A simple alternative to Navigation Compose for single-activity,
 * small-screen-count apps. Avoids introducing an extra dependency.
 */
sealed class Screen {
    /** The home / deck-list screen. */
    data object Home : Screen()

    /** The study screen for a specific deck. */
    data class Study(val deck: Deck) : Screen()
}

/**
 * AppNavigation — root composable that manages screen state.
 *
 * Switches between [HomeScreen] and [StudyScreen] using a simple
 * in-memory [Screen] state variable. No back-stack is maintained at
 * this step because there are only two screens with a clear one-way
 * Home → Study → Home flow.
 */
@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val screen = currentScreen) {
        is Screen.Home -> {
            HomeScreen(
                onStartStudying = { deck ->
                    currentScreen = Screen.Study(deck)
                }
            )
        }

        is Screen.Study -> {
            StudyScreen(
                deck   = screen.deck,
                onBack = { currentScreen = Screen.Home }
            )
        }
    }
}
