package com.github.app_server.data.db.di

import android.content.Context
import androidx.room.Room
import com.github.app_server.data.db.ReportsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideReportsRepository(@ApplicationContext context : Context) = Room.databaseBuilder(
        context = context,
        klass = ReportsDatabase::class.java,
        name = ReportsDatabase.DB_NAME
    ).build()

}