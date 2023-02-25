package com.star.wars.di

import android.content.Context
import com.star.wars.BuildConfig
import com.star.wars.BuildConfig.baseUrl
import com.star.wars.datasource.EndPoint.CACHE_SIZE
import com.star.wars.datasource.EndPoint.timeOut
import com.star.wars.datasource.remote.service.ApiService
import com.star.wars.datasource.remote.interceptor.OfflineInterceptor
import com.star.wars.utility.ConnectionUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    //    Hilt Provide Network Cache
    @Provides
    @Singleton
    fun provideRequestCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE)
    //OfflineInterceptor
    @Provides
    @Singleton
    fun provideOfflineInterceptor(
        connectionUtil: ConnectionUtil
    ) = OfflineInterceptor(connectionUtil = connectionUtil)

    //LoggingInterceptor
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

    //OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        offlineInterceptor: OfflineInterceptor,
        cache: Cache
    ): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(timeOut, TimeUnit.SECONDS)
        readTimeout(timeOut, TimeUnit.SECONDS)
        writeTimeout(timeOut, TimeUnit.SECONDS)
        addInterceptor(loggingInterceptor)
        addInterceptor(offlineInterceptor)
        cache(cache)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory).build()


    //ApiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}
