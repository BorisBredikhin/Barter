package ru.bredikhinpechnnikov.barter.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import ru.bredikhinpechnnikov.barter.data.LoginRepository
import ru.bredikhinpechnnikov.barter.data.Result
import ru.bredikhinpechnnikov.barter.userToken

import ru.bredikhinpechnnikov.barter.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String)  = loginRepository.login(username, password)

    fun register(
        firstName: String,
        lastName: String,
        username: String,
        birthday: String,
        primaryActivity: String,
        phoneNumber: String,
        password: String,
        repeatedPassword: String
    ){
        loginRepository.register(firstName, lastName, username, birthday, primaryActivity, phoneNumber, password, repeatedPassword)
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}