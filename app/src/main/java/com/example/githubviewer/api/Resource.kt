package com.example.githubviewer.api

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(var message: String?, var data: T? = null) : Resource<T>()
}
