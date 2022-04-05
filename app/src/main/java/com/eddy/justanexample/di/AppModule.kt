package com.eddy.justanexample.di

import android.content.Context
import com.eddy.justanexample.db.AppDatabase
import com.eddy.justanexample.db.CurrencyDao
import com.eddy.justanexample.base.BaseApplication
import com.eddy.justanexample.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun provideApplication(@ApplicationContext app: Context): BaseApplication {
    return app as BaseApplication
  }

  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

  @Singleton
  @Provides
  fun provideCurrencyDao(db: AppDatabase) = db.getCurrencyDao()

  @Singleton
  @Provides
  fun provideCurrencyRepository(currencyDao: CurrencyDao) = CurrencyRepository(currencyDao)
}