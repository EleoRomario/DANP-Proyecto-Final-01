package com.idnp.danp_proyecto_final.screens

import android.widget.RatingBar
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.navegation.navList
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.Secundary
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import org.intellij.lang.annotations.JdkConstants
import java.util.*
import kotlin.math.absoluteValue

/*
Eleo
* */

@Composable
fun ListDepartamentosScreen(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Perú", fontSize = 25.sp)},
            )
        }
    ) {
        ListDepBodyContent(navController)
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListDepBodyContent(navController: NavController){
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
                Image(imageVector = ImageVector.vectorResource(R.drawable.ic_app), contentDescription = "grid", modifier = Modifier
                    .clickable {
                        navController.navigate(route = AppScreens.GridDepartamentos.route)
                    }
                    .size(15.dp, 15.dp))
            }
            Spacer( modifier = Modifier.padding(vertical = 10.dp))
            SliderCards(navController)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun SliderCards(navController: NavController){

    val pagerState  = rememberPagerState(
        pageCount = departamentosList.size,
        initialPage =  0
    )

    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(
    ) {
        HorizontalPager(state = pagerState,
        ) { page ->
            Box(modifier = Modifier
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
    Box(
    ){
        Box(Modifier.padding(bottom = 20.dp)) {
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
                    .padding(bottom = 80.dp), fontSize = 25.sp, color = Color.White)

            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ){
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

                ) {
                Text("Explorar", fontSize = 20.sp, color = Primary)
            }
        }
    }
}


//@Preview(showSystemUi = true)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    //CardDepartameto()
    ListDepartamentosScreen(navController)
}
