package com.idnp.danp_proyecto_final.room.presentation.edit.components

import android.annotation.SuppressLint
import android.text.TextUtils.indexOf
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun departamentoDropdown(
    text: String,
    hint: String,
    onTextChange: (String) -> Unit,
) {
    Log.d("LIST","->"+text)
    val types = listOf("Cultural", "Aventura", "Extremo")

    val default = if(text=="")0 else types.indexOf(text)

    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(types[default]) } // (1)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded // (2)
        },
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        OutlinedTextField(
            readOnly = true, // (3)
            value = selectedType, // (4)
            onValueChange = { },
            label = { Text(hint) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon( // (5)
                    expanded = expanded
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            types.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedType = selectionOption
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                    onTextChange(selectionOption)
                }
            }
        }
    }
}
@Preview
@Composable
fun PreviewDropdown(){
    departamentoDropdown(
        text = "Extremo",
        hint = "Categoria",
        onTextChange = {}
    )
}