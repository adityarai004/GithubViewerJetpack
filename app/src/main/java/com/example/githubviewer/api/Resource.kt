package com.example.githubviewer.api

import com.example.githubviewer.data.models.APIError

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    data class Error<T>(val apiError: APIError?) : Resource<T>()
}
