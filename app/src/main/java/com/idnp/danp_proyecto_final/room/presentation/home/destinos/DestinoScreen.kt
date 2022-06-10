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
import com.idnp.danp_proyecto_final.room.presentation.Screen
import com.idnp.danp_proyecto_final.room.presentation.home.HomeEvent
import com.idnp.danp_proyecto_final.room.presentation.home.HomeViewModel
import com.idnp.danp_proyecto_final.room.presentation.home.components.DepartamentoItem


@Composable
fun DestinoScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            DestinoTopBar()
        },
        floatingActionButton = {
            DestinoFab(
                onFabClicked = { navController.navigate(Screen.Edit.route) }
            )
        },
        content = { innerPadding ->
            DestinoContent(
                modifier = Modifier.padding(innerPadding),
                onDeletedepartamento = { viewModel.onEvent(HomeEvent.Deletedepartamento(it)) },
                onEditdepartamento = {
                    navController.navigate(
                        route = Screen.Edit.passId(it)
                    )
                },
                departamentos = state.departamentos
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
    onDeletedepartamento: (departamento: Departamento) -> Unit,
    onEditdepartamento: (id: Int?) -> Unit,
    departamentos: List<Departamento> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn {
            items(departamentos) { departamento ->

            }
        }
    }
}

@Composable
fun DestinoFab(
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = {  }
) {
    FloatingActionButton(
        onClick = onFabClicked,
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
    DestinoContent(onDeletedepartamento = {}, onEditdepartamento = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewDestinoFab() {
    DestinoFab()
}

@Preview(showBackground = true)
@Composable
fun PreviewDestinoTopBar() {
    DestinoTopBar()
}