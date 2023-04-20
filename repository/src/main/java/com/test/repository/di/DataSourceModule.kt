package com.test.repository.di

import com.test.repository.remote.http.datasources.ApiCatsDataSource
import com.test.repository.remote.http.interfaces.IApiCatsDataSource
import com.test.repository.remote.http.services.CatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideApiCatsDataSource(api: CatService): IApiCatsDataSource = ApiCatsDataSource(api)
}


