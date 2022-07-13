package com.idnp.danp_proyecto_final.domain.viewsmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.idnp.danp_proyecto_final.data.datastore.DestinoDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel
@Inject
constructor(
    private val destinoDataStore: DestinoDataStore
): ViewModel(){

    val destinoPrefs = destinoDataStore.favoritesPrefsFlow.asLiveData()

    fun insertDataStore(
        favorite: Boolean,
        title: String, image: String,
        description: String,
        category: String,
        departamento: String,
        latitud: String,
        longitud: String
    ){
        viewModelScope.launch {
            destinoDataStore.favoriteDestino(favorite,title,image,description,category,departamento,latitud,longitud)
        }
    }
}