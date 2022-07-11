package com.idnp.danp_proyecto_final.presentation.home.departamentos

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.components.BottomBarNavegation
import com.idnp.danp_proyecto_final.presentation.components.TopBarBack
import com.idnp.danp_proyecto_final.ui.theme.Primary

@Composable
fun ListDepartamentosScreen(
    state: DepartamentoListState,
    navController: NavController
){
    Scaffold (
        topBar = {
            TopBarBack(navController)
        },
        bottomBar = {
            BottomBarNavegation(1,navController)
        }
    ){

        val viewModel: DepartamentosViewModel = hiltViewModel()
        val isRefreshing = viewModel.isRefreshing.collectAsState()

        ListBodyContent(
            state = state,
            isRefreshing = isRefreshing.value,
            refreshData = viewModel::getDepartamentoList,
            navController
        )
    }
}

@Composable
fun ListBodyContent(
    state: DepartamentoListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    navController: NavController
){
    Column(modifier = Modifier
        .padding(horizontal = 30.dp)
    ){
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = refreshData
        ){
            GridCards(
                state,
                navController
            )
        }
    }
}

@Composable
fun GridCards(
    state: DepartamentoListState,
    navController: NavController
){
    Column(
        Modifier.padding(bottom = 75.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Departamentos del Perú", fontSize = 15.sp, color = Primary)
        }
        Spacer( modifier = Modifier.padding(vertical = 10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(
                start = 0.dp,
                top = 16.dp,
                end = 0.dp,
                bottom = 16.dp
            )
        ){
            items(
                state.departamentos
            ){ departamento ->
                CardDep(title = departamento.title, img = departamento.image , navController = navController, code=departamento.id)

            }
        }
    }
}

@Composable
fun CardDep(
    title:String,
    img:String,
    navController: NavController,
    code:String
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .height(150.dp)
            .width(150.dp)
            .clickable {
                navController.navigate(route = AppScreens.DetalleDepartamento.route + "/" + code)
            }
            .padding(4.dp),
    ){
        Image(
            painter = rememberImagePainter(
                data = img,
                builder = {
                    placeholder(R.drawable.placeholder)

                }
            ),
            contentDescription = "null",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
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
            .padding(bottom = 10.dp), fontSize = 15.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun GridDefaultPreview() {
    val navController = rememberNavController()
    //ListDepartamentosScreen(navController)
}