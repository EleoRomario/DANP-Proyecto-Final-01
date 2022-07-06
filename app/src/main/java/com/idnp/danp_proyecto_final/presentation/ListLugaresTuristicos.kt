package com.idnp.danp_proyecto_final.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.ui.theme.Primary

/*
Renato
* */
@Composable
fun ListLugaresTuristicoScreen(navController: NavController, code: String?){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val departamento = departamentosList.first {
        it.code == code
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)},
        topBar = {
            TopBarDepBack(departamento.title,scope,scaffoldState,navController)
        },
        bottomBar = {
            BottomBarNavegation(-1,navController)
        },


    ){
        listLugaresBodyContent(departamento.title,code, navController)
    }
}

@Composable
fun listLugaresBodyContent(title:String, code: String?, navController: NavController){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
    ) {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Column(
            Modifier.padding(bottom = 75.dp)
        ) {

            Text("Destinos Turisticos", fontSize = 20.sp, color = Primary)
            Spacer( modifier = Modifier.padding(vertical = 5.dp))
            cardsLugaresTuristicos(title,code, navController)
        }
    }
}

@Composable
fun cardsLugaresTuristicos(title: String, code: String?, navController: NavController){
    val destinos = destinosList.filter{
        it.codeDep == code
    }
    LazyColumn(){
        items(destinos.size){ destino ->
            cardLugarTuristico(title,destinos[destino].title, destinos[destino].img, code, destinos[destino].code ,navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListLugarDefaultPreview() {
    val navController = rememberNavController()
    ListLugaresTuristicoScreen(navController,"arequipa")
}