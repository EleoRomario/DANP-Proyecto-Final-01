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
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.DepartamentosData
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.Primary

/*
Eleo
* */
@Composable
fun GridDepartamentosScreen(navController: NavController){
    Scaffold {
        CenterAlignedTopAppBar(
            title = { Text("Perú") },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu), contentDescription = "menu")
                }
            }
        )
        GridBodyContent(navController)
    }
}

@Composable
fun GridBodyContent(navController: NavController){
    Column(modifier = Modifier
        .padding(all = 20.dp)
        .padding(top = 60.dp)) {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {newText -> text = newText},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = Color.Gray,
                    disabledBorderColor = Color.Gray,
                    disabledTextColor = Color.Black
                ),
                shape = RoundedCornerShape(20.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "searchIcon")
                },
                placeholder = {
                    Text(text = "Buscar destino")
                }
            )
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_list), contentDescription = "grid", modifier = Modifier.weight(weight = 0.1f).clickable { navController.navigate(route = AppScreens.ListDepartamentos.route) })
        }
        Spacer(modifier = Modifier.size(20.dp))
        //CardDepartamento()
        GridCards(departamentosList)
    }
}

@Composable
fun GridCards(departamentos: List<DepartamentosData>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(
                departamentos.size
            ){ departamento ->
                Box(modifier = Modifier.padding(10.dp)){
                    CardDep(title = departamentos[departamento].title, img = departamentos[departamento].imgUri)
                }
            }
        }
    )


}

@Composable
fun CardDep(title:String, img:Int){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(200.dp)
            .width(200.dp)
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
    //GridDepartamentosScreen()
}