package com.idnp.danp_proyecto_final.di

import android.app.Application
import androidx.room.Room
import com.idnp.danp_proyecto_final.room.data.repository.DepartamentoRepositoryImpl
import com.idnp.danp_proyecto_final.room.data.repository.DestinoRepositoryImpl
import com.idnp.danp_proyecto_final.room.data.source.local.AppDatabase
import com.idnp.danp_proyecto_final.room.domain.repository.DepartamentoRepository
import com.idnp.danp_proyecto_final.room.domain.repository.DestinoRepository
import com.idnp.danp_proyecto_final.utils.DATABASE_NAME
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
    fun provideAppDatabase(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRepository(db: AppDatabase): DepartamentoRepository{
        return DepartamentoRepositoryImpl(db.departamentoDao)
    }
    @Provides
    @Singleton
    fun provideDRepository(db: AppDatabase): DestinoRepository {
        return DestinoRepositoryImpl(db.destinoDao)
    }
}