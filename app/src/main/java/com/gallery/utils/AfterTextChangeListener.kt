package com.gallery.utils

import android.text.Editable
import android.text.TextWatcher

internal class AfterTextChangeListener(private val callback: (Editable?) -> Unit) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        callback.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Not needed
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Not needed
    }

}