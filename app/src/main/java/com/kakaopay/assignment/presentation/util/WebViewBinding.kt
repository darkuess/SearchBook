package com.kakaopay.assignment.presentation.util

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindJavaScriptEnabled")
fun WebView.bindJavaScriptEnabled(flag: Boolean) {
    this.settings.javaScriptEnabled = flag
}