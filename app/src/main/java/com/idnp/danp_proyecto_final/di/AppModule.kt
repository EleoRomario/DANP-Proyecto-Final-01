package com.idnp.danp_proyecto_final.di

import android.app.Application
import androidx.paging.PagingConfig
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar
import com.idnp.danp_proyecto_final.data.repository.DepartamentosRepositoryImpl
import com.idnp.danp_proyecto_final.data.repository.FirestorePagingSource
import com.idnp.danp_proyecto_final.domain.repository.DepartamentosRepositoy
import com.idnp.danp_proyecto_final.domain.use_case.GetDepartamentos
import com.idnp.danp_proyecto_final.domain.use_case.UseCases
import com.idnp.danp_proyecto_final.room.data.repository.DepartamentoRepositoryImpl
import com.idnp.danp_proyecto_final.room.data.repository.DestinoRepositoryImpl
import com.idnp.danp_proyecto_final.room.data.source.local.AppDatabase
import com.idnp.danp_proyecto_final.room.domain.repository.DepartamentoRepository
import com.idnp.danp_proyecto_final.room.domain.repository.DestinoRepository
import com.idnp.danp_proyecto_final.utils.DATABASE_NAME
import com.idnp.danp_proyecto_final.utils.DEPARTAMENTOS
import com.idnp.danp_proyecto_final.utils.PAGE_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @Provides
    @Singleton
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideDepartamentoList(
        firestore: FirebaseFirestore
    ) = firestore.collection("departamentos")

    @Provides
    fun provideQueryDepartamentos() = FirebaseFirestore.getInstance()
        .collection(DEPARTAMENTOS)
        .limit(PAGE_SIZE)

    @Provides
    fun provideFirestorePagingSource(
        queryDepartamentos: Query
    ) = FirestorePagingSource(queryDepartamentos)

    @Provides
    fun providePaginConfig() = PagingConfig(
        pageSize = PAGE_SIZE.toInt()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideDepartamentosRepository(
        source: FirestorePagingSource,
        config: PagingConfig
    ): DepartamentosRepositoy = DepartamentosRepositoryImpl(source, config)

    @Provides
    fun provideUseCases(
        repository: DepartamentosRepositoy
    ) = UseCases(
        getDepartamentos = GetDepartamentos(repository)
    )
}