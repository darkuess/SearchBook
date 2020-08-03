package com.kakaopay.assignment.presentation.util

import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ComponentActivity.setContentViewBinding(@LayoutRes layoutId: Int): T {
    return DataBindingUtil.setContentView<T>(this, layoutId)
        .apply { lifecycleOwner = this@setContentViewBinding }
}