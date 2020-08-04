package com.kakaopay.assignment.presentation.util

import android.content.ContextWrapper
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.g4s8.rxkeyboard.RxAndroidSoftKeyboard
import com.g4s8.rxkeyboard.RxSoftKeyboard
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import timber.log.Timber

fun View.getLifeCycleOwner(): LifecycleOwner? {
    var context = context

    while (context is ContextWrapper) {
        if (context is LifecycleOwner) {
            return context
        }
        context = context.baseContext
    }

    return null
}

fun View.hideKeyboard() {
    RxAndroidSoftKeyboard(context).hide(
        this.windowToken,
        RxSoftKeyboard.HideFlags.NONE
    ).autoDispose(
        AndroidLifecycleScopeProvider.from(
            getLifeCycleOwner(),
            Lifecycle.Event.ON_DESTROY
        )
    ).subscribe({}, Timber::e)
}