package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.R
import kotlinx.coroutines.launch

/*
Renato
* */
@Composable
fun ListLugaresTuristicoScreen(navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "back",
                        )

                    }
                },
                title = { Text("Perú", color = Color.White, fontSize = 25.sp) },
                actions = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_white), contentDescription = "menu")
                    }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),

                )
        },
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)}
    ){
        listLugaresBodyContent()
    }
}

@Composable
fun listLugaresBodyContent(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardLugarTuristico(){
    Card() {
        Row() {
            Image(painter = painterResource(R.drawable.arequipa) , contentDescription = "")
            Box(){
                Column() {
                    Row() {
                        Text("Titulo")
                        //Image(imageVector = ImageVector.vectorResource(), contentDescription = )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListLugarDefaultPreview() {
    val navController = rememberNavController()
    ListLugaresTuristicoScreen(navController)
}