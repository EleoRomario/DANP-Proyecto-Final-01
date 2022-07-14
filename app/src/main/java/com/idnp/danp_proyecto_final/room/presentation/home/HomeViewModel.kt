package com.idnp.danp_proyecto_final.room.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idnp.danp_proyecto_final.room.domain.use_cases.DeleteDepartamento
import com.idnp.danp_proyecto_final.room.domain.use_cases.GetDepartamentoWithDestinos
import com.idnp.danp_proyecto_final.room.domain.use_cases.GetDepartamentos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deletedepartamento: DeleteDepartamento,
    //getdepartamentos: GetDepartamentos,
    getDepartamentosWithDestinos: GetDepartamentoWithDestinos
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getDepartamentosWithDestinos().onEach { departamentos ->
            _state.value = state.value.copy(
                departamentos = departamentos
            )
        }.launchIn(viewModelScope)
    }
}