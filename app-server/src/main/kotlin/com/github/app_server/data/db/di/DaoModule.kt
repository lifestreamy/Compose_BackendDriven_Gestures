package com.github.app_server.data.db.di

import com.github.app_server.data.db.ReportsDao
import com.github.app_server.data.db.ReportsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun provideReportsDao(db : ReportsDatabase) : ReportsDao = db.reportsDao()

}