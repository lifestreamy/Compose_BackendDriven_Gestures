package com.github.app_server.di

import com.github.app_server.data.db.ReportsRepositoryImpl
import com.github.app_server.domain.db.ReportsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindReportsRepository(repo: ReportsRepositoryImpl) : ReportsRepository

}