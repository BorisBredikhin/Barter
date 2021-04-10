package ru.bredikhinpechnnikov.barter.net

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

public object Config{
    const val BACKEND_ADDRESS = "http://10.0.2.2:8000"
}

public val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
