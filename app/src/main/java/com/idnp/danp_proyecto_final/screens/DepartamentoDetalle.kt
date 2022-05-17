package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.DepartamentosData
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.ui.theme.Primary

/*
Eleo
* */

@Composable
fun DetalleDepartamentoScreen(navController: NavController, departamento:String?){
    Scaffold {
        DetalleContent(departamento)
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
            title = { Text("Perú", color = Color.White) },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_white), contentDescription = "menu")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),

        )

    }
}
@Composable
fun DetalleContent(code:String?){

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
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = departamento.title,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = departamento.description,
                color = Color.White,
                fontSize = 18.sp
                )
            Button(
                onClick = { /*TODO*/ },
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

@Preview(showBackground = true)
@Composable
fun DetalleDefaultPreview() {
    //DetalleDepartamentoScreen()
}