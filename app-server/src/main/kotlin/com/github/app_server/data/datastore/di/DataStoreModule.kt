package com.github.app_server.data.datastore.di

import android.content.Context
import com.github.app_server.data.datastore.DefaultDataStore
import com.github.app_server.data.datastore.DefaultUserSettingsRepository
import com.github.app_server.di.ApplicationScope
import com.github.app_server.domain.datastore.UserSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Provides
    @Singleton
    fun provideDefaultDataStore(
        @ApplicationContext context: Context,
        @ApplicationScope scope: CoroutineScope
    ) = DefaultDataStore(context)


    @Provides
    @Singleton
    fun provideUserSettingsRepository(ds: DefaultDataStore)
    : UserSettingsRepository = DefaultUserSettingsRepository(ds)

}