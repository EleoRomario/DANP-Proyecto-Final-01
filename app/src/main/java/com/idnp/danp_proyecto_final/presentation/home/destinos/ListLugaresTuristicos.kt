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
import com.idnp.danp_proyecto_final.domain.viewsmodel.SharedViewModel
import com.idnp.danp_proyecto_final.presentation.components.*
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentoListState
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentosViewModel
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DestinoListState
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos
import com.idnp.danp_proyecto_final.room.presentation.edit.EditViewModel
import com.idnp.danp_proyecto_final.room.presentation.edit.destinos.DestinoEditViewModel
import com.idnp.danp_proyecto_final.room.presentation.home.HomeViewModel
import com.idnp.danp_proyecto_final.ui.theme.Primary

@Composable
fun ListLugaresTuristicoScreen(
    state: DepartamentoListState,
    navController: NavController,
    code: String?,
    viewModel: DepartamentosViewModel = hiltViewModel(),
    viewModelState: DataStoreViewModel = hiltViewModel(),
    roomView: EditViewModel = hiltViewModel(),
    roomDestinoView: DestinoEditViewModel = hiltViewModel(),
    roomDepartamentos: HomeViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val stateDestino = viewModel.stateD.value

    val roomdb = roomDepartamentos.state.value

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
            viewModelState,
            roomView,
            roomDestinoView,
            roomdb.departamentos.size,
        )
    }
}

@Composable
fun ListLugaresBodyContent(
    stateD: DestinoListState,
    departamentoTitle:String,
    navController: NavController,
    viewModelState: DataStoreViewModel,
    roomView: EditViewModel,
    roomDestinoView: DestinoEditViewModel,
    departamentos: Int,
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
                viewModelState,
                roomView,
                roomDestinoView,
                departamentos,
            )
        }
    }
}

@Composable
fun CardsLugaresTuristicos(
    departamentoTitle: String,
    stateD: DestinoListState,
    navController: NavController,
    viewModelState: DataStoreViewModel,
    roomView: EditViewModel,
    roomDestinoView: DestinoEditViewModel,
    size: Int,
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
                destino.category,
                navController,
                viewModelState,
                roomView,
                roomDestinoView,
                size,
            )
        }
    }
}