package com.idnp.danp_proyecto_final.domain.viewsmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.idnp.danp_proyecto_final.domain.model.SharedDestino


class SharedViewModel: ViewModel() {
    var destino by mutableStateOf<SharedDestino?>(null)
        private set

    fun addDestino(newDestino: SharedDestino){
        destino = newDestino
    }
}