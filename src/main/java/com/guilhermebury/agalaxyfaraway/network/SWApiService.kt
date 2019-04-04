package com.guilhermebury.agalaxyfaraway.network

import com.google.gson.Gson

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

object SWApiService {
    val API_BASE_URL = "https://swapi.co/api/"

    fun <S> createService(serviceClass: Class<S>): S {

        //requisitions interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(httpClient.build())
                .build()

        return retrofit.create(serviceClass)
    }
}