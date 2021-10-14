package com.unava.dia.tictactoe.di

import com.unava.dia.tictactoe.data.Game
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGame() : Game {
        return Game()
    }
}