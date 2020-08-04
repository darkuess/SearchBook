package com.kakaopay.assignment.presentation.util

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("bindTriggerSearch")
fun EditText.bindTriggerSearch(boolean: Boolean) {
    this.hideKeyboard()
}