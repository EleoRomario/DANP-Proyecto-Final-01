package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.PrimaryAlpha

@Composable
fun CategoryDestinosScreen(navController: NavController,category:String?){
    Scaffold (
        topBar = {
            TopBarBack(navController)
        },
        bottomBar = {
            BottomBarNavegation(2,navController)
        }
    ){
        CategoryBodyContent(category,navController)
    }
}

@Composable
fun CategoryBodyContent(category:String?, navController: NavController){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
    ){
        Categories(category, navController)
    }
}
@Composable
fun Categories(category:String?, navController: NavController){
    var isSelected by remember{
        mutableStateOf(false)
    }
    Column() {
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
        ListCardsCategory(category, navController)
        Spacer(modifier = Modifier.height(100.dp))
    }
}
@Composable
fun ListCardsCategory(category : String?, navController: NavController){
    val destinos = destinosList.filter{
        it.category == category
    }
    LazyColumn(){
        items(destinos.size){ destino ->
            var departamento = departamentosList.first{
                it.code == destinos[destino].codeDep
            }
            cardLugarTuristico(departamento.title,destinos[destino].title, destinos[destino].img, category, destinos[destino].code ,navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryDefaultPreview() {
    val navController = rememberNavController()
    //CardDepartameto()
    CategoryDestinosScreen(navController,"extremo", )
}