package com.idnp.danp_proyecto_final.room.presentation.home.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos

@Composable
fun DepartamentoItem(
    modifier: Modifier = Modifier,
    departamento: DepartamentoWithDestinos,
    onEditDepartamento: () -> Unit,
    onDeleteDepartamento: () -> Unit,
    onDestinos: () -> Unit
) {
    Log.d("DEP","ItemDep"+departamento.departamento.id)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "${departamento.departamento.title}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "destinos: ${departamento.destinos.size}",
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Row {
                IconButton(onClick = onDestinos){
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = null,
                        tint = Color.Cyan
                    )
                }
                IconButton(onClick = onEditDepartamento) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Cyan
                    )
                }
                IconButton(onClick = onDeleteDepartamento) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewdepartamentoItem() {
        /*DepartamentoItem(
            departamento = DepartamentoWithDestinos(
                departamento = Departamento(
                    title = "Arequippa",
                    description = "Hola",
                    image = "imagen"
                ),
                destinos = ""
            ),
            onEditDepartamento = {},
            onDeleteDepartamento = {},
            onDestinos = {}
        )*/
}