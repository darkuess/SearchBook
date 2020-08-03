package com.kakaopay.assignment.module.network

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor


object LogInterceptor {
    fun getLogInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}