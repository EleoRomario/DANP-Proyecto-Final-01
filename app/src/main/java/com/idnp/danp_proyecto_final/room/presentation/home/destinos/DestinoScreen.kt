package com.idnp.danp_proyecto_final.room.presentation.home.destinos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.presentation.Screen
import com.idnp.danp_proyecto_final.room.presentation.home.HomeEvent
import com.idnp.danp_proyecto_final.room.presentation.home.HomeViewModel
import com.idnp.danp_proyecto_final.room.presentation.home.components.DepartamentoItem
import com.idnp.danp_proyecto_final.room.presentation.home.components.DestinoItem


@Composable
fun DestinoScreen(
    navController: NavController,
    departamentoId: Int?,
    viewModel: DestinoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            DestinoTopBar()
        },
        floatingActionButton = {
            DestinoFab(
                departamento = departamentoId,
                onFabClicked = {
                    navController.navigate(
                        route = Screen.DestinoEdit.passId(it,-1)
                    )
                }
            )
        },
        content = { innerPadding ->
            DestinoContent(
                modifier = Modifier.padding(innerPadding),
                onDeletedestino = { viewModel.onEvent(DestinoEvent.DeleteDestino(it)) },
                onEditdestino = {
                    navController.navigate(
                        route = Screen.DestinoEdit.passId(departamentoId,it)
                    )
                },
                destinos = state.destinos
            )
        }
    )
}

@Composable
fun DestinoTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.destinos),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun DestinoContent(
    modifier: Modifier = Modifier,
    onDeletedestino: (destino: Destino) -> Unit,
    onEditdestino: (id: Int?) -> Unit,
    destinos: List<Destino> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn {
            items(destinos) { destino ->
                DestinoItem(
                    destino = destino,
                    onEditDestino = { onEditdestino(destino.id) },
                    onDeleteDestino = { onDeletedestino(destino)}
                )

            }
        }
    }
}

@Composable
fun DestinoFab(
    modifier: Modifier = Modifier,
    departamento: Int?,
    onFabClicked: (id: Int?) -> Unit
) {
    FloatingActionButton(
        onClick = { onFabClicked(departamento) },
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = stringResource(id = R.string.add_destino))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDestinoContent() {
    DestinoContent(onDeletedestino = {}, onEditdestino = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewDestinoFab() {
    //DestinoFab()
}

@Preview(showBackground = true)
@Composable
fun PreviewDestinoTopBar() {
    DestinoTopBar()
}