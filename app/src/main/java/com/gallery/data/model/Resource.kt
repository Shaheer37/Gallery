package com.gallery.data.model

sealed class Resource<out T> {
    data class Valid<T>(val data: T) : Resource<T>()
    data class Invalid<T>(val message: String, val data: T? = null) : Resource<T>()
}