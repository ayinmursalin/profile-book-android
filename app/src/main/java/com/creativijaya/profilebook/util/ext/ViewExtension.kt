package com.creativijaya.profilebook.util.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.view.isVisible
import com.creativijaya.profilebook.MyAppConstant
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.util.widget.DebounceClickListener
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import java.io.File

fun View.clickWithDebounce(interval: Int = MyAppConstant.DEBOUNCE_TIME, callback: (View?) -> Unit) {
    setOnClickListener(DebounceClickListener(interval, callback))
}

@Suppress("DEPRECATION")
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.visibleIf(shouldVisible: Boolean) {
    this.isVisible = shouldVisible
}

fun TextInputLayout.showFormError(message: String, requestFocus: Boolean = false) {
    isErrorEnabled = true
    error = message

    if (requestFocus) {
        requestFocus()
    }
}

fun TextInputLayout.setIsEditable(isEditable: Boolean, isClickableInput: Boolean = false) {
    this.editText?.apply {
        isFocusable = isEditable && isClickableInput.not()
        isFocusableInTouchMode = isEditable && isClickableInput.not()
        isLongClickable = isEditable && isClickableInput.not()
        isClickable = isEditable && isClickableInput
    }
}

fun ImageView.loadImageUrl(imageUrl: String) {
    if (imageUrl.isEmpty()) return

    Picasso.get()
        .load(imageUrl)
        .fit()
        .centerCrop()
        .error(R.drawable.common_ic_broken_image)
        .into(this)
}
