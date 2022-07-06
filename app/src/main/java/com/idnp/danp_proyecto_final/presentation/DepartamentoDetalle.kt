package com.idnp.danp_proyecto_final.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.ColorWhiteAlpha
import com.idnp.danp_proyecto_final.ui.theme.Primary

/*
Eleo
* */

@Composable
fun DetalleDepartamentoScreen(navController: NavController, departamento:String?){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)},
    ){
        DetalleContent(departamento,navController)
        TopBarMenu(scope,scaffoldState,navController)
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
                .padding(top=200.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isExpanded) ColorWhiteAlpha else Color.Transparent)
            ) {
                Text(
                    text = departamento.title,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                    ,
                    textAlign = TextAlign.Center,
                    color = if(isExpanded) Primary else Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = departamento.description,
                    color = if(isExpanded) Primary else Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(20.dp)
                )
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
                    modifier = Modifier.padding(20.dp)
                        ,

                    ) {
                    Text("Destinos", fontSize = 20.sp, color = Primary)
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
}