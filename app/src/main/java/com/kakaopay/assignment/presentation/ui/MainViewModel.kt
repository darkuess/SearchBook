package com.kakaopay.assignment.presentation.ui

import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.AuthCodeClient
import com.kakao.sdk.auth.rx
import com.kakaopay.assignment.MainApplication
import com.kakaopay.assignment.presentation.base.AutoDisposeViewModel
import com.uber.autodispose.autoDispose
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : AutoDisposeViewModel() {

    init {
        authorize()
    }

    private fun authorize() {
        AuthCodeClient.rx.authorizeUsingCustomTabs(context = context)
            .observeOn(Schedulers.io())
            .flatMap { authCode -> AuthApiClient.rx.issueAccessToken(authCode) }
            .autoDispose(this)
            .subscribe({ token -> Timber.d("$token") }, Timber::e)
    }

    companion object {
        private val context = MainApplication.INSTANCE
    }
}