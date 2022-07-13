package com.idnp.danp_proyecto_final.domain.viewsmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.idnp.danp_proyecto_final.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class DepartamentosViewModel
@Inject
constructor(
    useCases: UseCases
): ViewModel(){
    val departamentos = useCases.getDepartamentos().cachedIn(viewModelScope)
}