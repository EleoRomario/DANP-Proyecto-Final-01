package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.DepartamentosData
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.Secundary
import com.idnp.danp_proyecto_final.ui.theme.TextAlt
import kotlinx.coroutines.launch

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
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_1),
                            contentDescription = "back",
                        )

                    }
                },
                title = { Text(departamento.title, color = Primary, fontSize = 25.sp) },
                actions = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_1), contentDescription = "menu")
                    }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),

                )
        },
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)}
    ){
        listLugaresBodyContent(departamento.title,code, navController)
    }
}

@Composable
fun listLugaresBodyContent(title:String, code: String?, navController: NavController){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)) {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Column() {

            Spacer( modifier = Modifier.padding(vertical = 10.dp))

            Text("Destinos Turisticos", fontSize = 20.sp, color = Primary)
            Spacer( modifier = Modifier.padding(vertical = 10.dp))
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