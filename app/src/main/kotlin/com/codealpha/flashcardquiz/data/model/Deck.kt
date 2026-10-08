package com.codealpha.flashcardquiz.data.model

/**
 * Represents a deck of flashcards grouped under a common topic.
 *
 * @param id    Unique identifier for this deck.
 * @param title Human-readable name for the deck.
 * @param cards The list of [Flashcard]s contained in this deck.
 */
data class Deck(
    val id: Int,
    val title: String,
    val cards: List<Flashcard>,
)
