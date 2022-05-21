package com.idnp.danp_proyecto_final.screens

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.ui.theme.ColorWhiteAlpha
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.TextAlt

@Composable
fun SearchDestinosScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)},
        topBar = {
            TopBarBackMenu(scope, scaffoldState,navController)
        },
        bottomBar = {
            BottomBarNavegation(3,navController)
        },
    ){
        val textVal = remember { mutableStateOf(TextFieldValue("")) }
        Column(
            Modifier.padding(horizontal = 30.dp)
                .padding(bottom = 75.dp)
        ) {
            SearchAppBar(textVal)
            SearchDestinosList(textVal,navController)
        }
    }
}
@Composable
fun SearchAppBar(textVal: MutableState<TextFieldValue>){
    TextField(
        value = textVal.value,
        onValueChange = {
            textVal.value = it
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        placeholder = { Text(text = "Busca tu próximo destino") },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search"
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = ColorWhiteAlpha,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        trailingIcon = {
            if(textVal.value != TextFieldValue("")){
                IconButton(onClick = {
                    textVal.value = TextFieldValue("")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(25.dp)
                    )
                }
            }
        },
        singleLine = true
    )
}
@Composable
fun SearchDestinosList(textVal: MutableState<TextFieldValue>, navController: NavController){

    var destinos = destinosList.filter{
        var title = it.title.lowercase()
        title.contains(textVal.value.text.lowercase())
    }
    LazyColumn(){
        items(destinos.size){destino ->
            var depTitle = departamentosList.first{
                it.code == destinos[destino].codeDep
            }
            cardLugarTuristico(depTitle.title,destinos[destino].title,destinos[destino].img,destinos[destino].codeDep,destinos[destino].code, navController)
        }
    }
}


@Preview
@Composable
fun searchPreview(){
    val navController = rememberNavController()
    SearchDestinosScreen(navController)
}
