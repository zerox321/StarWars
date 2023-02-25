package com.star.wars.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.paging.PagingConfig
import com.star.wars.datasource.EndPoint.pageSize
import com.star.wars.utility.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilityModule {

    @Provides
    @Singleton
    fun providePagingConfig() = PagingConfig(pageSize = pageSize)

    @Provides
    @Singleton
    fun provideAnimationView() = AnimationView()

    @Provides
    @Singleton
    fun provideHtmlUtility() = HtmlUtility()

    @Provides
    @Singleton
    fun provideTextToSpeechController(@ApplicationContext appContext: Context) =
        TextToSpeechController(appContext)

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) =
        ContextCompat.getSystemService(
            context,
            ConnectivityManager::class.java
        ) as ConnectivityManager

    @Provides
    @Singleton
    fun provideConnectionUtil(manger: ConnectivityManager) = ConnectionUtil(manger)

    @Provides
    @Singleton
    fun provideToastUtility(@ApplicationContext appContext: Context) = ToastUtility(appContext)

}
