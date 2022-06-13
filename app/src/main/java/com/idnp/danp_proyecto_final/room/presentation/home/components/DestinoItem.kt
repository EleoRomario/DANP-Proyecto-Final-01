package com.idnp.danp_proyecto_final.room.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.idnp.danp_proyecto_final.room.domain.model.Destino


@Composable
fun DestinoItem(
    modifier: Modifier = Modifier,
    destino: Destino,
    onEditDestino: () -> Unit,
    onDeleteDestino: () -> Unit,
) {
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
                    text = "${destino.title}",
                    style = MaterialTheme.typography.h6
                )
            }
            Row {
                IconButton(onClick = onEditDestino) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }
                IconButton(onClick = onDeleteDestino) {
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
fun PreviewDestinoItem() {
    DestinoItem(
        destino = Destino(
            codeDep = 1,
            title = "Arequipa",
            description = "hola",
            image = "R.drawable.amazonas",
            latitud = 12.5555,
            longitud = 12.5555,
            category = "aventura"
        ),
        onEditDestino = {},
        onDeleteDestino = {}
    )
}