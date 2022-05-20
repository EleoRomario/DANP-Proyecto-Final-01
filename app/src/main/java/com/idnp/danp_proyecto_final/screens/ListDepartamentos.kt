package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.idnp.danp_proyecto_final.data.DepartamentosData
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.Secundary
import com.idnp.danp_proyecto_final.ui.theme.TextAlt

/*
Eleo
* */
@Composable
fun ListDepartamentosScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopBarBack(navController)
        },
        bottomBar = {
            BottomBarNavegation(1,navController)
        }
            ){
        ListBodyContent(navController)
    }
}

@Composable
fun ListBodyContent(navController: NavController){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
    ){
          GridCards(departamentosList, navController)
    }
}

@Composable
fun GridCards(departamentos: List<DepartamentosData>, navController: NavController){
    Column(){
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Departamentos del Perú", fontSize = 15.sp, color = Primary)
        }
        Spacer( modifier = Modifier.padding(vertical = 10.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(
                start = 0.dp,
                top = 16.dp,
                end = 0.dp,
                bottom = 16.dp
            ),
            content = {
                items(
                    departamentos.size
                ){ departamento ->
                    Box(modifier = Modifier.padding(10.dp)){
                        CardDep(title = departamentos[departamento].title, img = departamentos[departamento].imgUri,navController, departamentos[departamento].code)
                    }
                }
            }
        )
    }
}

@Composable
fun CardDep(title:String, img:Int, navController: NavController, code:String){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(150.dp)
            .width(150.dp)
            .clickable {
                navController.navigate(route = AppScreens.DetalleDepartamento.route + "/" + code)
            },
    ){
        Image(painterResource(img), contentDescription = "null", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xCE000C1F)
                        )
                    )
                )
        )
        Text(title, modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 10.dp), fontSize = 20.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun GridDefaultPreview() {
    val navController = rememberNavController()
    ListDepartamentosScreen(navController)
}