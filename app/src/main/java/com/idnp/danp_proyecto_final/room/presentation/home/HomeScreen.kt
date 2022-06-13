package com.idnp.danp_proyecto_final.room.presentation.home

import android.util.Log
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
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.presentation.Screen
import com.idnp.danp_proyecto_final.room.presentation.home.components.DepartamentoItem
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFab(
                onFabClicked = { navController.navigate(Screen.Edit.route) }
            )
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeletedepartamento = { viewModel.onEvent(HomeEvent.Deletedepartamento(it.departamento)) },
                onEditdepartamento = {
                    navController.navigate(
                        route = Screen.Edit.passId(it)
                    )
                },
                onDestinos = {
                             navController.navigate(
                                 route = Screen.DestinoHome.passId(it)
                             )
                },
                departamentos = state.departamentos
            )
        }
    )
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.departamentos),
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
fun HomeContent(
    modifier: Modifier = Modifier,
    onDeletedepartamento: (departamentoWithDestinos: DepartamentoWithDestinos) -> Unit,
    onEditdepartamento: (id: Int?) -> Unit,
    onDestinos: (id: Int?) -> Unit,
    departamentos: List<DepartamentoWithDestinos> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn {
            items(departamentos) { departamento ->
                Log.d("DEP","codeDep"+departamento.departamento.id)
                DepartamentoItem(
                    departamento = departamento,
                    onEditDepartamento = { onEditdepartamento(departamento.departamento.id) },
                    onDeleteDepartamento = { onDeletedepartamento(departamento) },
                    onDestinos = {onDestinos(departamento.departamento.id)}
                )
            }
        }
    }
}

@Composable
fun HomeFab(
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
        Icon(imageVector = Icons.Outlined.Add, contentDescription = stringResource(id = R.string.add_departamento))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewdepartamentoContent() {
        HomeContent(onDeletedepartamento = {}, onEditdepartamento = {}, onDestinos = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewdepartamentoFab() {
        HomeFab()
}

@Preview(showBackground = true)
@Composable
fun PreviewdepartamentoTopBar() {
        HomeTopBar()
}

