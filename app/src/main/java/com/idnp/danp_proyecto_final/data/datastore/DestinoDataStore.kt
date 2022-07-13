package com.idnp.danp_proyecto_final.data.datastore

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

const val FAVORITES_PREFFERENCES_NAME: String = "favorites_preferences"

@Singleton
class DestinoDataStore
    @Inject
    constructor(private val context: Application
    ) {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            FAVORITES_PREFFERENCES_NAME)

    val favoritesPrefsFlow: Flow<FavoriteDestino>
    get() = context.dataStore.data
        .catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map { preferences ->
            val isFavorite = preferences[FAVORITE_KEY] ?: false
            val destinoTitle = preferences[TITLE] ?: ""
            val destinoImage = preferences[IMAGE] ?: ""
            val destinoDescription = preferences[DESCRIPTION] ?: ""
            val destinoCategory = preferences[CATEGORY] ?: ""
            val destinoDepartamento = preferences[DEPARTAMENTO] ?: ""
            val destinoLatitud = preferences[LATITUD] ?: ""
            val destinoLongitud = preferences[LONGITUD] ?: ""

            FavoriteDestino(
                isFavorite = isFavorite,
                title = destinoTitle,
                image = destinoImage,
                description = destinoDescription,
                category = destinoCategory,
                departamento = destinoDepartamento,
                latitud = destinoLatitud,
                longitud = destinoLongitud
            )
        }

    suspend fun favoriteDestino(
        favorite: Boolean,
        title: String,
        image: String,
        description: String,
        category: String,
        departamento: String,
        latitud: String,
        longitud: String
    ){

        context.dataStore.edit { preferences ->
            preferences[FAVORITE_KEY] = favorite
            preferences[TITLE] = title
            preferences[IMAGE] = image
            preferences[DESCRIPTION] = description
            preferences[CATEGORY] = category
            preferences[DEPARTAMENTO] = departamento
            preferences[LATITUD]= latitud
            preferences[LONGITUD] = longitud
        }
    }

    companion object{
        val FAVORITE_KEY = booleanPreferencesKey("destino_favorite")
        val TITLE = stringPreferencesKey("destino_title")
        val IMAGE = stringPreferencesKey("destino_image")
        val DESCRIPTION = stringPreferencesKey("destino_description")
        val CATEGORY = stringPreferencesKey("destino_categoy")
        val DEPARTAMENTO = stringPreferencesKey("destino_departamento")
        val LATITUD = stringPreferencesKey("destino_latitud")
        val LONGITUD = stringPreferencesKey("destino_longitud")
    }
    }

data class FavoriteDestino(
    val isFavorite: Boolean,
    val title: String,
    val image: String,
    val description: String,
    val category: String,
    val departamento: String,
    val latitud: String,
    val longitud: String
)

