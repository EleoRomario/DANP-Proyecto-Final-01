package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.Secundary
import com.idnp.danp_proyecto_final.ui.theme.TextAlt
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
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_1),
                            contentDescription = "back",
                        )

                    }
                },
                title = { Text("Perú", color = Primary, fontSize = 25.sp) },
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
        listLugaresBodyContent()
    }
}

@Composable
fun listLugaresBodyContent(){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)) {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Column() {
            OutlinedTextField(
                value = text,
                onValueChange = {newText -> text = newText},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Secundary
                ),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "searchIcon")
                },
                placeholder = {
                    Text(text = "Buscar destino")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer( modifier = Modifier.padding(vertical = 10.dp))

            Text("Destinos Turisticos", fontSize = 20.sp, color = Primary)
            Spacer( modifier = Modifier.padding(vertical = 10.dp))
            cardsLugaresTuristicos()
        }
    }
}

@Composable
fun cardsLugaresTuristicos(){
    cardLugarTuristico()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardLugarTuristico(){
    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(0.1.dp, color = Color.LightGray),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.arequipa),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp, 110.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Titulo", fontSize = 20.sp, color = Primary)
                        Image(imageVector = ImageVector.vectorResource(R.drawable.ic_heart), contentDescription = "favorite")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "location")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Ubicación", color = TextAlt)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(imageVector = ImageVector.vectorResource(R.drawable.ic_star), contentDescription = "calification")
                        Text(text = "4.5")

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