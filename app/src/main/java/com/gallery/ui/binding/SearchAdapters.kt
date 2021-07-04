package com.gallery.ui.binding

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.gallery.ui.listing.GalleryViewModel
import com.gallery.ui.listing.SearchClearer
import com.google.android.material.textfield.TextInputEditText


@BindingAdapter("onSearchPressed")
fun onSearchPressed(etSearch: EditText, viewModel: GalleryViewModel) {

    etSearch.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            viewModel.onSearchQueryUpdated(etSearch.text.toString())
            hideKeyboard(etSearch)
            true
        } else {
            false
        }
    }

    etSearch.setOnKeyListener { _, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            viewModel.onSearchQueryUpdated(etSearch.text.toString())
            hideKeyboard(etSearch)
            true
        } else {
            false
        }
    }

}


private fun hideKeyboard(view: View) {
    view.clearFocus()
    val inputMethodManager: InputMethodManager =
        view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


@BindingAdapter("app:clearSearch")
fun clearSearch(ivCross: ImageView, clearer: SearchClearer) {

    ivCross.setOnClickListener {
        hideKeyboard(ivCross)
        clearer.clearSearch()
    }
}