package ru.bredikhinpechnnikov.barter

import android.content.SharedPreferences
import androidx.core.content.edit

var SharedPreferences.userToken
    get() = getString("USER_TOKEN", null)
    set(value) {
        edit {
            putString("USER_TOKEN", value)
        }
    }