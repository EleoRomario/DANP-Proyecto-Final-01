package com.idnp.danp_proyecto_final.presentation.home.destinos

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.domain.viewsmodel.DataStoreViewModel
import com.idnp.danp_proyecto_final.presentation.components.*
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentoListState
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentosViewModel
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DestinoListState
import com.idnp.danp_proyecto_final.ui.theme.Primary

/*
Renato
* */
@Composable
fun ListLugaresTuristicoScreen(
    state: DepartamentoListState,
    navController: NavController,
    code: String?,
    viewModel: DepartamentosViewModel = hiltViewModel(),
    viewModelState: DataStoreViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val stateDestino = viewModel.stateD.value

    val departamento = state.departamentos.first {
        it.id == code
    }


    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController) },
        topBar = {
            TopBarDepBack(
                departamento.title,
                scope,
                scaffoldState,
                navController
            )
        },
        bottomBar = {
            BottomBarNavegation(-1,navController)
        },


    ){
        ListLugaresBodyContent(
            stateDestino,
            departamento.title,
            navController,
            viewModelState
        )
    }
}

@Composable
fun ListLugaresBodyContent(
    stateD: DestinoListState,
    departamentoTitle:String,
    navController: NavController,
    viewModelState: DataStoreViewModel
){
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
            CardsLugaresTuristicos(
                departamentoTitle,
                stateD,
                navController,
                viewModelState
            )
        }
    }
}

@Composable
fun CardsLugaresTuristicos(
    departamentoTitle: String,
    stateD: DestinoListState,
    navController: NavController,
    viewModelState: DataStoreViewModel
){
    val destinos = stateD.destinos


    LazyColumn(){
        items(destinos){ destino ->
            CardLugarTuristico(
                departamentoTitle,
                destino.title,
                destino.description,
                destino.image,
                destino.latitud,
                destino.longitud,
                navController,
                viewModelState
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListLugarDefaultPreview() {
    val navController = rememberNavController()
   // ListLugaresTuristicoScreen(navController,"arequipa")
}