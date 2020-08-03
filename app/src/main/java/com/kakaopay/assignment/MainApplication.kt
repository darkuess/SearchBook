package com.kakaopay.assignment

import android.app.Application
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.SdkLogger
import com.kakao.sdk.common.rx
import com.kakao.sdk.common.util.Utility
import com.kakaopay.assignment.module.networkModule
import com.kakaopay.assignment.module.repositoryModules
import com.kakaopay.assignment.module.useCaseModules
import com.kakaopay.assignment.module.viewModuleModules
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.Completable
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application() {

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKakaoSdk()
        initDI()
    }

    override fun startActivity(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivityRx(intent)
    }

    private fun initDI() {
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
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

    private fun startActivityRx(intent: Intent) {
        Completable.fromAction { super.startActivity(intent) }
            .autoDispose(scopeProvider)
            .subscribe({}, Timber::e)
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

        private val appModule = listOf(
            networkModule,
            repositoryModules,
            useCaseModules,
            viewModuleModules
        )
    }
}