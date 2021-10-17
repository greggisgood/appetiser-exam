package ph.greggjover.appetiserexam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ph.greggjover.appetiserexam.data.network.RetrofitService
import javax.inject.Singleton

/**
 * Dependency Injection class that provides the dependency for [RetrofitService]
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService() = RetrofitService.create()
}