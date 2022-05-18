package com.idnp.danp_proyecto_final.screens

import android.text.Layout
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.DepartamentosData
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.ColorTopBar
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.PrimaryAlpha
import kotlinx.coroutines.launch

/*
Eleo
* */

@Composable
fun DetalleDepartamentoScreen(navController: NavController, departamento:String?){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)}
    ){
        DetalleContent(departamento,navController)
        /**
         * TopAppBar
         * **/
        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xA1000C1F),
                        Color.Transparent
                    )
                )
            )
        ) {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
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
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_white),
                            contentDescription = "menu"
                        )
                    }

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),

                )
        }
    }
}
@Composable
fun DetalleContent(code:String?, navController: NavController){

    val departamento = departamentosList.first {
        it.code == code
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(departamento.imgUri),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .height(500.dp)
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
        var isExpanded by remember{
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(30.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if(isExpanded) ColorTopBar else Color.Transparent)
            ) {
                Text(
                    text = departamento.title,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                    ,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = departamento.description,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(20.dp)
                )
            }
            Button(
                onClick = {
                          navController.navigate(AppScreens.ListLugaresTuristico.route + "/" + code)
                },
                contentPadding = PaddingValues(
                    start = 30.dp,
                    top = 12.dp,
                    end = 30.dp,
                    bottom = 12.dp
                ),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.padding(20.dp),

            ) {
                Text("Destinos", fontSize = 20.sp, color = Primary)
            }
        }
    }
}

@Composable
fun modal(navController: NavController){
    Column(
        Modifier
            .background(color = Primary)
            .fillMaxHeight()
    ) {
        Text(text = "Perú", fontSize = 25.sp ,color= Color.White,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
        ){
            items(departamentosList) { departamento ->
                Card(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate(route = AppScreens.DetalleDepartamento.route + "/" + departamento.code)
                    },
                    backgroundColor = PrimaryAlpha
                ) {
                    Text(text = departamento.title,
                        Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleDefaultPreview() {
    val navController = rememberNavController()
    DetalleDepartamentoScreen(navController, "arequipa")
    //modal()
}