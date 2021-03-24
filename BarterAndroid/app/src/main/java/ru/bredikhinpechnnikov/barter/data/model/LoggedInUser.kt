package ru.bredikhinpechnnikov.barter.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val token: String,
    val displayName: String? = null
)