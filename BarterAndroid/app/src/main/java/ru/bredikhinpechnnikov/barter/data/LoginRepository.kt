package ru.bredikhinpechnnikov.barter.data

import ru.bredikhinpechnnikov.barter.data.model.LoggedInUser
import ru.bredikhinpechnnikov.barter.net.AuthProvider

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val provider: AuthProvider) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        provider.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = provider.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    fun register(
        firstName: String,
        lastName: String,
        username: String,
        birthday: String,
        primaryActivity: String,
        phoneNumber: String,
        password: String,
        repeatedPassword: String
    ): Result<String> {
        return provider.register(firstName, lastName, username, birthday, primaryActivity, phoneNumber, password, repeatedPassword)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}