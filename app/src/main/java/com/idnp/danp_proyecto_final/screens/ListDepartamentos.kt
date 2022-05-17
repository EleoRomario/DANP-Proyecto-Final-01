package com.idnp.danp_proyecto_final.screens

import android.widget.RatingBar
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.Primary
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import java.util.*
import kotlin.math.absoluteValue

/*
Eleo
* */

@Composable
fun ListDepartamentosScreen(navController: NavController){
    Scaffold {
        CenterAlignedTopAppBar(
            title = {Text("Perú")},
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu), contentDescription = "menu")
                }
            }
            )
        ListDepBodyContent(navController)
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListDepBodyContent(navController: NavController){
    Column(modifier = Modifier
        .padding(all = 20.dp)
        .padding(top = 60.dp)) {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
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
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_app), contentDescription = "grid", modifier = Modifier.weight(weight = 0.1f)
                .clickable { navController.navigate(route = AppScreens.GridDepartamentos.route) })
        }
        Spacer(modifier = Modifier.size(20.dp))
        SliderCards(navController)
    }
}

@ExperimentalPagerApi
@Composable
fun SliderCards(navController: NavController){

    val pagerState  = rememberPagerState(
        pageCount = departamentosList.size,
        initialPage =  2
    )

    Column(
    ) {
        HorizontalPager(state = pagerState,
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )

                }
                .fillMaxWidth()
                .padding(40.dp, 0.dp, 40.dp, 0.dp),
                //shape = RoundedCornerShape(20.dp)
            ) {

                val newDepartamento = departamentosList[page]
                CardDepartamento(code = newDepartamento.code,title = newDepartamento.title, img = newDepartamento.imgUri, navController)

            }

        }

    }

}

@Composable
fun CardDepartamento(code:String, title:String, img:Int, navController: NavController){
    Column(
        Modifier.background(Color.Transparent)
    ){
        Box(modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(400.dp)
            .fillMaxWidth()
        ){
        Image(painterResource(img), contentDescription = "null", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Box(
            modifier = Modifier
                .height(250.dp)
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
            .padding(bottom = 80.dp), fontSize = 40.sp, color = Color.White)

    }
        Button(
            onClick = {
                      navController.navigate(route = AppScreens.DetalleDepartamento.route + "/" + code)
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .layout{
                    measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(constraints.maxWidth, constraints.maxHeight){
                    placeable.placeRelative(constraints.maxWidth / 2 - placeable.width / 2, -1 * placeable.height / 2)
                }
            },
        ) {
            Text("Explorar", fontSize = 20.sp, color = Primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //CardDepartamento()
    //ListDepartamentosScreen()
}
