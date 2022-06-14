package com.idnp.danp_proyecto_final.room.presentation.edit

import android.net.Uri
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
import com.idnp.danp_proyecto_final.room.presentation.edit.components.departamentoImage
import com.idnp.danp_proyecto_final.room.presentation.edit.components.departamentoInputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditScreen(
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel()
) {
    val titleState = viewModel.departamentoTitle.value
    val descriptionState = viewModel.departamentoDescription.value
    val imageState = viewModel.departamentoImage.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EditViewModel.UiEvent.SaveDepartamento -> {
                    navController.navigateUp()
                }
            }
        }
    }
    
    Scaffold(
        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.add_departamento)
            )
        },
        content = {
            EditContent(
                title = titleState.text,
                description = descriptionState.text,
                image = imageState.img,
                onEvent = { viewModel.onEvent(it) }
            )
        },
        bottomBar = {
            EditBottomBar(
                onInsertdepartamento = { viewModel.onEvent(EditEvent.InsertDepartamento) }
            )
        }
    )
}

@Composable
fun EditTopBar(topAppBarText: String) {
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
    image: Uri,
    onEvent: (EditEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(44.dp))
        departamentoInputText(
            text = title,
            hint = stringResource(id = R.string.title),
            onTextChange = { onEvent(EditEvent.EnteredTitle(it)) }
        )
        departamentoInputText(
            text = description,
            hint = stringResource(id = R.string.description),
            onTextChange = { onEvent(EditEvent.EnteredDescription(it)) }
        )
        departamentoImage(
            image = image,
            onImageChange = { onEvent(EditEvent.EnteredImage(it)) }
        )
    }
}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertdepartamento: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 14.dp),
        onClick = { onInsertdepartamento() }
    ) {
        Text(text = stringResource(id = R.string.save_departamento))
    }
}

@Preview
@Composable
fun PreviewAddEditdepartamentoTopBar() {
        EditTopBar(
            topAppBarText = stringResource(id = R.string.add_departamento)
        )
}

@Preview
@Composable
fun PreviewAddEditdepartamentoContent() {
        EditContent(
            title = "Ada",
            description = "Smith",
            image = Uri.parse(""),
            onEvent = { }
        )
}

@Preview
@Composable
fun PreviewAddEditBottomBar() {
        EditBottomBar {}
}