package com.mangary.app.domain.model

/**
 * Domain model representing a Manga
 * Clean architecture entity - independent of data source
 */
data class Manga(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String?,
    val status: String,
    val contentRating: String,
    val year: Int?,
    val tags: List<String>,
    val author: String?,
    val artist: String?
)

/**
 * Sealed class representing the result of an operation
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String, val exception: Exception? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
