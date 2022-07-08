package com.idnp.danp_proyecto_final.presentation.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.components.TopBarPeru
import com.idnp.danp_proyecto_final.presentation.components.ProfileScreen
import com.idnp.danp_proyecto_final.presentation.login.user.LoginViewModel
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.PrimaryAlpha
import com.idnp.danp_proyecto_final.ui.theme.Secundary
import com.idnp.danp_proyecto_final.ui.theme.TextAlt
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopBarPeru()
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)){
                //HomeContent()

            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun HomeContent(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
        .verticalScroll(scrollState)
    ) {
        var text by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box() {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.machupicchu),
                    contentDescription = "bg_inicio",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Text("Encuentra tu próximo viaje",
                    fontSize = 25.sp,
                    color = TextAlt,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            //departamentos(navController)
            Spacer( modifier = Modifier.padding(vertical = 10.dp))
            //categorias(navController)
            Spacer(modifier = Modifier.height(75.dp))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun departamentos(navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Departamentos", fontSize = 15.sp, color = Primary)
            Text("Ver todos", fontSize = 13.sp, color = TextAlt,
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = AppScreens.ListDepartamentos.route)
                    }
            )
        }
        Spacer( modifier = Modifier.padding(vertical = 10.dp))
        SliderCards(navController)
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
        modifier = Modifier.fillMaxWidth()
    ){
            Box(modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(300.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(route = AppScreens.DetalleDepartamento.route + "/" + code)
                }
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
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp, start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_location), contentDescription = "location")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(title, fontSize = 20.sp, color = Color.White)
                }

            }
        }
}

@Composable
fun categorias(navController: NavController){
    Column() {
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Categorías", fontSize = 15.sp, color = Primary)

        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            cardCategoria("cultural",R.drawable.cultural, navController)
            cardCategoria("aventura",R.drawable.machupicchu, navController)
            cardCategoria("extremo", R.drawable.extremo,navController)
        }
    }

}
@Composable
fun cardCategoria(category:String, img: Int, navController: NavController){
    Box (
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .clickable {
                navController.navigate(route = AppScreens.CategoryDestinos.route + "/${category}")
            }
            ){
        Image(
            painter = painterResource(id = img),
            contentDescription = "categoria",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
          modifier = Modifier
              .fillMaxSize()
              .background(PrimaryAlpha)
        ){}
        Text(
            text = category,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
   // HomeScreen(navController)
}
