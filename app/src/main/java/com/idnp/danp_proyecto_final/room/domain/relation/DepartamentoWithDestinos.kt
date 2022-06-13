package com.idnp.danp_proyecto_final.room.domain.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.model.Destino

data class DepartamentoWithDestinos (
    @Embedded val departamento: Departamento,
    @Relation(
        entity = Destino::class,
        parentColumn = "id",
        entityColumn = "codeDep"
    )
    val destinos: List<Destino>
)