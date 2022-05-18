package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

/*
Eleo
* */
@Composable
fun GridDepartamentosScreen(navController: NavController){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Perú", fontSize = 25.sp)},
            )
        }
            ){
        GridBodyContent(navController)
    }
}

@Composable
fun GridBodyContent(navController: NavController){
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
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Departamentos", fontSize = 20.sp, color = Primary)
                Image(imageVector = ImageVector.vectorResource(R.drawable.ic_list), contentDescription = "list", modifier = Modifier
                    .clickable {
                        navController.navigate(route = AppScreens.ListDepartamentos.route)
                    }
                    .size(15.dp,15.dp)
                )
            }
            Spacer( modifier = Modifier.padding(vertical = 10.dp))
            GridCards(departamentosList, navController)
        }
    }

}

@Composable
fun GridCards(departamentos: List<DepartamentosData>, navController: NavController){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
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

@Composable
fun CardDep(title:String, img:Int, navController: NavController, code:String){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(200.dp)
            .width(200.dp)
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
    GridDepartamentosScreen(navController)
}