package com.kakaopay.assignment.presentation.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

fun <T : ViewDataBinding> ComponentActivity.setContentViewBinding(@LayoutRes layoutId: Int): T {
    return DataBindingUtil.setContentView<T>(this, layoutId)
        .apply { lifecycleOwner = this@setContentViewBinding }
}

fun <T : ViewDataBinding> Fragment.inflateBinding(
    inflater: LayoutInflater = LayoutInflater.from(context),
    @LayoutRes layoutId: Int,
    parent: ViewGroup? = null,
    attachToParent: Boolean = false
): T {
    val binding = DataBindingUtil.inflate<T>(inflater, layoutId, parent, attachToParent)
    binding.lifecycleOwner = this
    return binding
}

fun <T : ViewDataBinding> initBinding(
    context: Context,
    layoutId: Int,
    parent: ViewGroup? = null,
    attachToParent: Boolean = false,
    lifecycleOwner: LifecycleOwner? = null
): T {
    val inflater = LayoutInflater.from(context)
    val binding = DataBindingUtil.inflate<T>(inflater, layoutId, parent, attachToParent)
    binding.lifecycleOwner = lifecycleOwner
    return binding
}