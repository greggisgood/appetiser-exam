package ph.greggjover.appetiserexam.data.database.content

import ph.greggjover.appetiserexam.data.database.content.Content

/**
 * Data class that associates a genre with its content
 */
data class GenreWithContent(
    val genre: String,
    val content: List<Content>,
)