package com.test.repository.di

import android.content.Context
import com.test.repository.BuildConfig
import com.test.repository.remote.http.services.CatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(@ApplicationContext context: Context) = BuildConfig.API_URL

    @Provides
    fun provideCatService(retrofit: Retrofit): CatService =
        retrofit.create(CatService::class.java)

    @Provides
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        return clientBuilder.build()
    }
}

