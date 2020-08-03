package com.kakaopay.assignment

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.SdkLogger
import com.kakao.sdk.common.rx
import com.kakao.sdk.common.util.Utility
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import timber.log.Timber

class MainApplication : Application() {

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKakaoSdk()
    }

    private fun initKakaoSdk() {
        KakaoSdk.init(
            context = this,
            appKey = getString(R.string.kakao_app_key),
            loggingEnabled = BuildConfig.DEBUG
        )
        SdkLogger.rx.observable
            .autoDispose(scopeProvider)
            .subscribe { Timber.d("[SdkLogger] $it") }

        if (BuildConfig.DEBUG) {
            Timber.w("your debug android_key_hash = ${Utility.getKeyHash(this)}")
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var INSTANCE: MainApplication
            private set

        private val scopeProvider: AndroidLifecycleScopeProvider by lazy {
            AndroidLifecycleScopeProvider.from(
                ProcessLifecycleOwner.get(),
                Lifecycle.Event.ON_DESTROY
            )
        }
    }
}