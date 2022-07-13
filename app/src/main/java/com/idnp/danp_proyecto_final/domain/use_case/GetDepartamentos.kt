package com.idnp.danp_proyecto_final.domain.use_case

import com.idnp.danp_proyecto_final.domain.repository.DepartamentosRepositoy

class GetDepartamentos (
    private val repositoy: DepartamentosRepositoy
    ){
    operator fun invoke() = repositoy.getProducts()
}