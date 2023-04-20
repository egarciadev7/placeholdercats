package com.test.usecases.di

import com.test.repository.remote.http.interfaces.IApiCatsDataSource
import com.test.usecases.interfaces.IUseCaseCat
import com.test.usecases.usecases.UseCaseCat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Singleton
    @Provides
    fun provideUseCaseCat(
        apiCatsDataSource: IApiCatsDataSource,
    ): IUseCaseCat = UseCaseCat(
        apiCatsDataSource,
    )
}

