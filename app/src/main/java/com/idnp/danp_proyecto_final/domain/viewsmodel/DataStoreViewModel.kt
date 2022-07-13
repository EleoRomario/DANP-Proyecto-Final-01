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
    private val userDataStore: DestinoDataStore
): ViewModel(){

    val destinoPrefs = userDataStore.userPrefsFlow.asLiveData()

    fun insertDataStore(
        active: Boolean,
        name: String,
        photo: String,
        email: String,
        token: String,
        created: String,
    ){
        viewModelScope.launch {
            userDataStore.userLogin(active,name,photo,email,token,created)
        }
    }
}