package ph.greggjover.appetiserexam.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ph.greggjover.appetiserexam.data.database.AppDatabase
import javax.inject.Singleton
import ph.greggjover.appetiserexam.data.database.content.ContentDao

/**
 * Dependency Injection class that provides all Room dependencies in order for them to
 * be used in the app
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    /**
     * Instantiates an [AppDatabase] as a Singleton
     * @param context - Application Context
     */
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    /**
     * Instantiates [ContentDao] to be used by different classes
     */
    @Provides
    fun provideContentDao(appDatabase: AppDatabase) = appDatabase.contentDao()
}