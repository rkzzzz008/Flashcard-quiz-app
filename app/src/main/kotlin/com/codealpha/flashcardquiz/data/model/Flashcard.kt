package com.codealpha.flashcardquiz.data.model

/**
 * Represents a single flashcard with a question and its corresponding answer.
 *
 * @param id       Unique identifier for this card.
 * @param question The question displayed on the front of the card.
 * @param answer   The answer revealed on the back of the card.
 */
data class Flashcard(
    val id: Int,
    val question: String,
    val answer: String,
)
