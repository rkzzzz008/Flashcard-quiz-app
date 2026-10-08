package com.codealpha.flashcardquiz.ui.screen

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codealpha.flashcardquiz.data.model.Deck
import com.codealpha.flashcardquiz.data.repository.SampleData
import com.codealpha.flashcardquiz.ui.theme.FlashCardQuizTheme

/**
 * HomeScreen — the main landing screen of the FlashCard Quiz App.
 *
 * Displays the app branding and a list of available decks. Tapping
 * "Start Studying" on any deck card navigates to the [StudyScreen].
 *
 * @param onStartStudying Callback invoked with the chosen [Deck] when the
 *                        user presses "Start Studying".
 */
@Composable
fun HomeScreen(onStartStudying: (Deck) -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "icon_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.07f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Header ────────────────────────────────────────────────────
            item {
                HomeHeader(pulseScale = pulseScale)
                Spacer(modifier = Modifier.height(32.dp))
            }

            // ── Section label ─────────────────────────────────────────────
            item {
                Text(
                    text = "Available Decks",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.8.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // ── Deck cards ────────────────────────────────────────────────
            items(SampleData.allDecks, key = { it.id }) { deck ->
                DeckCard(
                    deck = deck,
                    onStartStudying = { onStartStudying(deck) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Header composable
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun HomeHeader(pulseScale: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App icon — pulsing circle with emoji
        Box(
            modifier = Modifier
                .size(88.dp)
                .scale(pulseScale)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            androidx.compose.ui.graphics.Color(0xFF4F46E5),
                            androidx.compose.ui.graphics.Color(0xFF7C3AED)
                        )
                    )
                )
                .semantics { contentDescription = "FlashCard Quiz app icon" },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🎓", fontSize = 40.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // App title
        Text(
            text = "FlashCard Quiz",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle
        Text(
            text = "Pick a deck and start mastering new topics\none card at a time.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f)
            ),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Deck card composable
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun DeckCard(deck: Deck, onStartStudying: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "Deck: ${deck.title}, ${deck.cards.size} cards" },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // Deck icon + title row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📚", fontSize = 26.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = deck.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${deck.cards.size} flashcards",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Start Studying button
            Button(
                onClick = onStartStudying,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                Text(
                    text = "Start Studying",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Previews
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Home — Light")
@Composable
private fun HomeScreenLightPreview() {
    FlashCardQuizTheme(darkTheme = false, dynamicColor = false) {
        HomeScreen(onStartStudying = {})
    }
}

@Preview(showBackground = true, name = "Home — Dark")
@Composable
private fun HomeScreenDarkPreview() {
    FlashCardQuizTheme(darkTheme = true, dynamicColor = false) {
        HomeScreen(onStartStudying = {})
    }
}
