package com.kakaopay.assignment.module.network

import android.content.Context
import com.kakaopay.assignment.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


private const val HEADER_AUTHORIZATION = "Authorization"

class BasicAuthInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().newBuilder()
            .addHeader(HEADER_AUTHORIZATION, context.getString(R.string.kakao_rest_api_key))
            .build()
            .run { chain.proceed(this) }
    }
}

private fun httpClient(context: Context): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(LogInterceptor.getLogInterceptor())
        .addInterceptor(BasicAuthInterceptor(context))
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

private fun retrofitClient(context: Context, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun createNetworkClient(context: Context) = retrofitClient(context, httpClient(context))