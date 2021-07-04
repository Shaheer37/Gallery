package com.gallery.utils.network

import java.io.IOException

class NoConnectivityException: IOException() {
        override val message: String?
            get() = "No Internet Connection"
    }