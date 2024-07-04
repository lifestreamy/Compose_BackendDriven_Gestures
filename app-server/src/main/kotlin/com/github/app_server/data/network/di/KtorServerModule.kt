package com.github.app_server.data.network.di

import com.github.app_server.data.network.KtorServerImpl
import com.github.network.domain.server.KtorServer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class KtorServerModule {

    @Binds
    @Singleton
    abstract fun bindKtorServerImpl(impl: KtorServerImpl) : KtorServer

}