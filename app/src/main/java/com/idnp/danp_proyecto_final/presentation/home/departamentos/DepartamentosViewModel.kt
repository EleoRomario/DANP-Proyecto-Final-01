package com.idnp.danp_proyecto_final.presentation.home.departamentos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.CollectionReference
import com.idnp.danp_proyecto_final.TurismoApp
import com.idnp.danp_proyecto_final.domain.repository.DepartamentosRepository
import com.idnp.danp_proyecto_final.domain.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartamentosViewModel
@Inject
constructor(
    private val departamentosRepository: DepartamentosRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state: MutableState<DepartamentoListState> = mutableStateOf(DepartamentoListState())
    val state: State<DepartamentoListState> = _state

    private val _stateD: MutableState<DestinoListState> = mutableStateOf(DestinoListState())
    val stateD: State<DestinoListState> = _stateD

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getDepartamentoList()
        savedStateHandle.get<String>("departamento")?.let{
            getDestinoList(it)
        }
    }

    fun getDepartamentoList(){
        departamentosRepository.getDepartamentosList().onEach { result ->
            when(result){
                is Result.Error -> {
                    _state.value = DepartamentoListState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = DepartamentoListState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = DepartamentoListState(departamentos = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getDestinoList(document: String){
        departamentosRepository.getDestinosList(document).onEach {result ->
            Log.d("DESTINOS", ">>>>"+result)
            when(result){
                is Result.Error -> {
                    _stateD.value = DestinoListState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _stateD.value = DestinoListState(isLoading = true)
                }
                is Result.Success -> {
                    _stateD.value = DestinoListState(destinos = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}