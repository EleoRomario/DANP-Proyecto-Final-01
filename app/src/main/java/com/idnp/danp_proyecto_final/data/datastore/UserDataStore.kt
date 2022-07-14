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

const val USER_PREFFERENCES_NAME: String = "favorites_preferences"

@Singleton
class DestinoDataStore
    @Inject
    constructor(private val context: Application
    ) {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            USER_PREFFERENCES_NAME)

    val userPrefsFlow: Flow<UserLogin>
    get() = context.dataStore.data
        .catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map { preferences ->
            val active = preferences[ACTIVE] ?: false
            val name = preferences[NAME] ?: ""
            val photo = preferences[PHOTO] ?: ""
            val email = preferences[EMAIL] ?: ""
            val token = preferences[TOKEN] ?: ""
            val created = preferences[CREATED] ?: ""

            UserLogin(
                active,
                name,
                photo,
                email,
                token,
                created
            )
        }

    suspend fun userLogin(
        active: Boolean,
        name: String,
        photo: String,
        email: String,
        token: String,
        created: String
    ){

        context.dataStore.edit { preferences ->
            preferences[ACTIVE] =active
            preferences[NAME] = name
            preferences[PHOTO] = photo
            preferences[EMAIL] = email
            preferences[TOKEN] = token
            preferences[CREATED] = created
        }
    }

    companion object{
        val ACTIVE = booleanPreferencesKey("user_active")
        val NAME = stringPreferencesKey("user_name")
        val PHOTO = stringPreferencesKey("user_photo")
        val EMAIL = stringPreferencesKey("user_email")
        val TOKEN = stringPreferencesKey("user_token")
        val CREATED = stringPreferencesKey("user_created")
    }
    }

data class UserLogin(
    val active: Boolean,
    val name: String,
    val photo: String,
    val email: String,
    val token: String,
    val created: String
)

