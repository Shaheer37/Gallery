package com.gallery.utils.network

import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val genericNetworkError = "An error occurred getting data from server."

fun getNetworkErrorMessage(e: Exception) = when (e) {
    is NoConnectivityException -> "You have no internet connection."
    is IOException -> "An Error occurred. Please check your network connection and try again."
    is SocketTimeoutException -> "Request timed out. Try again."
    is UnknownHostException -> "Unable to connect to server."
    else -> genericNetworkError
}

fun getErrorMessage(httpCode: Int) = when (httpCode) {
    // Invalid Data
    400 -> genericNetworkError
    // Time out error
    408 -> "Request timed out. Try again."
    // Internal server error
    500 -> "A server error occurred."
    // Any other error executing the API
    else -> genericNetworkError
}