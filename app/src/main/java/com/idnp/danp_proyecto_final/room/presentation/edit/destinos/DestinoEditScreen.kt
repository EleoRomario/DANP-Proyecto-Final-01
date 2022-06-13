package com.idnp.danp_proyecto_final.room.presentation.edit.destinos

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.room.presentation.edit.EditEvent
import com.idnp.danp_proyecto_final.room.presentation.edit.EditViewModel
import com.idnp.danp_proyecto_final.room.presentation.edit.components.departamentoImage
import com.idnp.danp_proyecto_final.room.presentation.edit.components.departamentoInputDouble
import com.idnp.danp_proyecto_final.room.presentation.edit.components.departamentoInputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DestinoEditScreen(
    navController: NavController,
    viewModel: DestinoEditViewModel = hiltViewModel()
) {
    val titleState = viewModel.destinoTitle.value
    val descriptionState = viewModel.destinoDescription.value
    val imageState = viewModel.destinoImage.value
    val categoyState = viewModel.destinoCategory.value
    val latitudState = viewModel.destinoLatitud.value
    val longitudState = viewModel.destinoLongitud.value

    Log.d("DEP","CurrentcodeDep"+viewModel.codeDep)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is DestinoEditViewModel.UiEvent.SaveDestino -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            DestinoEditTopBar(
                topAppBarText = stringResource(id = R.string.add_destino)
            )
        },
        content = {
            EditContent(
                title = titleState.text,
                description = descriptionState.text,
                image = imageState.img,
                category = categoyState.text,
                latitud = latitudState.text,
                longitud = longitudState.text,
                onEvent = { viewModel.onEvent(it) }
            )
        },
        bottomBar = {
            DestinoEditBottomBar(
                onInsertDestino = { viewModel.onEvent(DestinoEditEvent.InsertDestino) }
            )
        }
    )
}

@Composable
fun DestinoEditTopBar(topAppBarText: String) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun EditContent(
    title: String,
    description: String,
    image: String,
    category: String,
    latitud: Double,
    longitud: Double,
    onEvent: (DestinoEditEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(44.dp))
        departamentoInputText(
            text = title,
            hint = stringResource(id = R.string.destino_title),
            onTextChange = { onEvent(DestinoEditEvent.EnteredTitle(it)) }
        )
        departamentoInputText(
            text = description,
            hint = stringResource(id = R.string.destino_description),
            onTextChange = { onEvent(DestinoEditEvent.EnteredDescription(it)) }
        )
        departamentoInputText(
            text = category,
            hint = stringResource(id = R.string.destino_category),
            onTextChange = { onEvent(DestinoEditEvent.EnteredCategory(it)) }
        )
        departamentoInputDouble(
            numero = latitud,
            hint = stringResource(id = R.string.destino_latitud),
            onTextChange = { onEvent(DestinoEditEvent.EnteredLatitud(it.toDouble())) }
        )
        departamentoInputDouble(
            numero = longitud,
            hint = stringResource(id = R.string.destino_longitud),
            onTextChange = { onEvent(DestinoEditEvent.EnteredLongitud(it.toDouble())) }
        )
        departamentoImage(
            image = image,
            onImageChange = { onEvent(DestinoEditEvent.EnteredImage(it)) }
        )
    }
}

@Composable
fun DestinoEditBottomBar(
    modifier: Modifier = Modifier,
    onInsertDestino: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 14.dp),
        onClick = { onInsertDestino() }
    ) {
        Text(text = stringResource(id = R.string.add_destino))
    }
}

@Preview
@Composable
fun PreviewAddEditDestinoTopBar() {
    DestinoEditTopBar(
        topAppBarText = stringResource(id = R.string.add_destino)
    )
}

@Preview
@Composable
fun PreviewAddEditDestinoContent() {
    EditContent(
        title = "Ada",
        description = "Smith",
        image = "amazonas",
        category = "cultural",
        latitud = 12.5555,
        longitud = 512.25555,
        onEvent = { }
    )
}

@Preview
@Composable
fun PreviewAddEditDestinoBottomBar() {
    DestinoEditBottomBar {}
}