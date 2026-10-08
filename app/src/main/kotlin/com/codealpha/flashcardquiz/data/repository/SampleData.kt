package com.codealpha.flashcardquiz.data.repository

import com.codealpha.flashcardquiz.data.model.Deck
import com.codealpha.flashcardquiz.data.model.Flashcard

/**
 * In-memory sample data repository.
 *
 * Provides a pre-built "General Knowledge" deck with 10 flashcards.
 * Data is intentionally ephemeral — it resets on every app restart.
 * Persistent storage will be added in a later step.
 */
object SampleData {

    val generalKnowledgeDeck = Deck(
        id = 1,
        title = "General Knowledge",
        cards = listOf(
            Flashcard(
                id = 1,
                question = "What is the capital of France?",
                answer = "Paris"
            ),
            Flashcard(
                id = 2,
                question = "Which planet is known as the Red Planet?",
                answer = "Mars"
            ),
            Flashcard(
                id = 3,
                question = "What is the largest ocean on Earth?",
                answer = "The Pacific Ocean"
            ),
            Flashcard(
                id = 4,
                question = "Who wrote Romeo and Juliet?",
                answer = "William Shakespeare"
            ),
            Flashcard(
                id = 5,
                question = "How many sides does a hexagon have?",
                answer = "Six (6)"
            ),
            Flashcard(
                id = 6,
                question = "What is the chemical symbol for gold?",
                answer = "Au"
            ),
            Flashcard(
                id = 7,
                question = "Which country is the largest by land area?",
                answer = "Russia"
            ),
            Flashcard(
                id = 8,
                question = "What is the speed of light in a vacuum?",
                answer = "Approximately 299,792,458 metres per second (≈ 3 × 10⁸ m/s)"
            ),
            Flashcard(
                id = 9,
                question = "Who painted the Mona Lisa?",
                answer = "Leonardo da Vinci"
            ),
            Flashcard(
                id = 10,
                question = "What is the tallest mountain in the world?",
                answer = "Mount Everest (8,849 m / 29,032 ft)"
            ),
        )
    )

    /** All available decks. Extend this list when more decks are added. */
    val allDecks: List<Deck> = listOf(generalKnowledgeDeck)
}
