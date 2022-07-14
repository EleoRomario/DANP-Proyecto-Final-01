package com.idnp.danp_proyecto_final.presentation.home.destinos

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.data.models.Destino
import com.idnp.danp_proyecto_final.domain.viewsmodel.DataStoreViewModel
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.components.BottomBarNavegation
import com.idnp.danp_proyecto_final.presentation.components.CardLugarTuristico
import com.idnp.danp_proyecto_final.presentation.components.TopBarBack
import com.idnp.danp_proyecto_final.presentation.components.destinoCategoria
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentoListState
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentosViewModel
import com.idnp.danp_proyecto_final.room.presentation.edit.EditViewModel
import com.idnp.danp_proyecto_final.room.presentation.edit.destinos.DestinoEditViewModel
import com.idnp.danp_proyecto_final.room.presentation.home.HomeViewModel
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.PrimaryAlpha
import kotlinx.coroutines.tasks.await

@Composable
fun CategoryDestinosScreen(
    state: DepartamentoListState,
    navController: NavController,
    category:String?,
    viewModel: DepartamentosViewModel = hiltViewModel(),
    viewModelState: DataStoreViewModel = hiltViewModel(),
    roomView: EditViewModel = hiltViewModel(),
    roomDestinoView: DestinoEditViewModel = hiltViewModel(),
    roomDepartamentos: HomeViewModel = hiltViewModel()
){
    Scaffold (
        topBar = {
            TopBarBack(navController)
        },
        bottomBar = {
            BottomBarNavegation(2,navController)
        }
    ){
        CategoryBodyContent(
            state,
            category,
            navController,
            viewModel,
            viewModelState,
            roomView,
            roomDestinoView,
            roomDepartamentos
        )
    }
}

@Composable
fun CategoryBodyContent(
    state: DepartamentoListState,
    category:String?,
    navController: NavController,
    viewModel: DepartamentosViewModel,
    viewModelState: DataStoreViewModel,
    roomView: EditViewModel,
    roomDestinoView: DestinoEditViewModel,
    roomDepartamentos: HomeViewModel
){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
    ){
        Categories(
            state,
            category,
            navController,
            viewModel,
            viewModelState,
            roomView,
            roomDestinoView,
            roomDepartamentos
        )
    }
}
@Composable
fun Categories(
    state: DepartamentoListState,
    category:String?,
    navController: NavController,
    viewModel: DepartamentosViewModel,
    viewModelState: DataStoreViewModel,
    roomView: EditViewModel,
    roomDestinoView: DestinoEditViewModel,
    roomDepartamentos: HomeViewModel
){
    var isSelected by remember{
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.padding(bottom = 75.dp),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
                ){
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if(category == "cultural") PrimaryAlpha else Color.White,
                ),
                enabled = if(category == "cultural") false else true,
                onClick = {
                    navController.navigate(
                        route = AppScreens.CategoryDestinos.route + "/cultural")
                },
                border = BorderStroke(1.dp, Primary)
            ) {
                Text("Cultural", color = if(category == "cultural") Color.White else Primary,)
            }
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if(category == "aventura") PrimaryAlpha else Color.White,
                ),
                enabled = if(category == "aventura") false else true,
                onClick = {
                    navController.navigate(route = AppScreens.CategoryDestinos.route + "/aventura")
                },
                border = BorderStroke(1.dp, Primary)
            ) {
                Text("Aventura", color = if(category == "aventura") Color.White else Primary,)
            }
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if(category == "extremo") PrimaryAlpha else Color.White,
                ),
                enabled = if(category == "extremo") false else true,
                onClick = {
                    navController.navigate(AppScreens.CategoryDestinos.route + "/extremo")
                },
                border = BorderStroke(1.dp, Primary)
            ) {
                Text("Extremo", color = if(category == "extremo") Color.White else Primary,)
            }
        }
        ListCardsCategory(
            state,
            category,
            navController,
            viewModel,
            viewModelState,
            roomView,
            roomDestinoView,
            roomDepartamentos
        )
    }
}
@Composable
fun ListCardsCategory(
    state: DepartamentoListState,
    category : String?,
    navController: NavController,
    viewModel: DepartamentosViewModel,
    viewModelState: DataStoreViewModel,
    roomView: EditViewModel,
    roomDestinoView: DestinoEditViewModel,
    roomDepartamentos: HomeViewModel
){

    val roomdb = roomDepartamentos.state.value
    val stateDestino = viewModel.stateD.value
    Log.d("ELEO","--" + state.departamentos)
    val destinos = destinosList.filter{
        it.category == category
    }
    LazyColumn(){
        items(destinos.size){ destino ->
            var departamento = departamentosList.first{
                it.code == destinos[destino].codeDep
            }
            destinoCategoria(
                departamento.title,
                destinos[destino].title,
                destinos[destino].img,
                destinos[destino].codeDep,
                destinos[destino].code ,
                navController
            )
        }
    }
}

