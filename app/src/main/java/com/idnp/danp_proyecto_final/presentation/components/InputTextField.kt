package com.idnp.danp_proyecto_final.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String = "Helloo",
    inputType: KeyboardType = KeyboardType.Number,
    visualTransformation: VisualTransformation,
    onTextChange: (String) -> Unit,
) {


    OutlinedTextField(
        modifier = modifier,
        label = { Text(label) },
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType
        ),
        singleLine = true,
        visualTransformation = visualTransformation,

        )

}