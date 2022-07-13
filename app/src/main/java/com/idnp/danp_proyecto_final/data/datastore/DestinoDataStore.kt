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
            Log.d("FAVORITE","->>"+isFavorite+destinoTitle)
            FavoriteDestino(
                isFavorite = isFavorite,
                title = destinoTitle
            )
        }

    suspend fun favoriteDestino(
        favorite: Boolean,
        title: String
    ){

        context.dataStore.edit { preferences ->
            preferences[FAVORITE_KEY] = favorite
            preferences[TITLE] = title
        }
    }

    companion object{
        val FAVORITE_KEY = booleanPreferencesKey("destino_favorite")
        val TITLE = stringPreferencesKey("destino_title")
    }
    }

data class FavoriteDestino(
    val isFavorite: Boolean,
    val title: String,
)

