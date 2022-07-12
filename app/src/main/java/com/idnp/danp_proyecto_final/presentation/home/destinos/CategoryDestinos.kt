package com.idnp.danp_proyecto_final.presentation.home.destinos

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.components.BottomBarNavegation
import com.idnp.danp_proyecto_final.presentation.components.TopBarBack
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentoListState
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentosViewModel
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DestinoListState
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.PrimaryAlpha

@Composable
fun CategoryDestinosScreen(
    state: DepartamentoListState,
    navController: NavController,category:String?
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
            navController
        )
    }
}

@Composable
fun CategoryBodyContent(
    state: DepartamentoListState,
    category:String?,
    navController: NavController
){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
    ){
        Categories(state, category, navController)
    }
}
@Composable
fun Categories(
    state: DepartamentoListState,
    category:String?,
    navController: NavController
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
        //ListCardsCategory(state, category, navController)
    }
}
/*@Composable
fun ListCardsCategory(
    state: DepartamentoListState,
    category : String?,
    navController: NavController
){

    val viewModel: DepartamentosViewModel = hiltViewModel()

    val destinosList = state.departamentos.map {
        viewModel.getDestinoList(it.id.toString())
        val stateD = viewModel.stateD.value
    }

    var dep: String =""
    var codeDep: String = ""

    var titleDep: String = ""
    var codeDestino: String = ""
    val destinos = stateD.destinos.filter{
        codeDestino = it.id
        titleDep = it.title
        it.category == category
    }


    val destino = stateD.destinos.first{
        it.id == codeDestino
    }

    LazyColumn(){
        items(destinos){ destino->
            cardLugarTuristico(
                dep,
                titleDep,
                destino.image,
                codeDep,
                codeDestino,
                navController
            )
        }
    }
}*/

@Preview(showBackground = true)
@Composable
fun CategoryDefaultPreview() {
    val navController = rememberNavController()
    //CardDepartameto()
    //CategoryDestinosScreen(navController,"aventura", )
}
