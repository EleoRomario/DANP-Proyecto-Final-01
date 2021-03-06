package com.idnp.danp_proyecto_final.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import android.util.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.*
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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.domain.model.SharedDestino
import com.idnp.danp_proyecto_final.domain.viewsmodel.DataStoreViewModel
import com.idnp.danp_proyecto_final.domain.viewsmodel.SharedViewModel
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.navegation.navList
import com.idnp.danp_proyecto_final.room.presentation.edit.EditEvent
import com.idnp.danp_proyecto_final.room.presentation.edit.EditViewModel
import com.idnp.danp_proyecto_final.room.presentation.edit.destinos.DestinoEditEvent
import com.idnp.danp_proyecto_final.room.presentation.edit.destinos.DestinoEditViewModel
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.ui.theme.PrimaryAlpha
import com.idnp.danp_proyecto_final.ui.theme.TextAlt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBarPeru(){
    CenterAlignedTopAppBar(
        title = { Text("Per??", fontSize = 25.sp) },
    )
}

@Composable
fun TopBarBack(navController: NavController){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            },
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_1),
                    contentDescription = "back",
                )

            }
        },
        title = { Text("Per??", fontSize = 25.sp) },
    )
}
@Composable
fun TopBarBackMenu(scope: CoroutineScope,scaffoldState: ScaffoldState,navController: NavController){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            },
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_1),
                    contentDescription = "back",
                )

            }
        },
        title = { Text("Per??", fontSize = 25.sp) },
        actions = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_1), contentDescription = "menu")
            }

        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
    )
}

@Composable
fun TopBarDepBack(title:String,scope: CoroutineScope,scaffoldState: ScaffoldState,navController: NavController){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            },
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_1),
                    contentDescription = "back",
                )

            }
        },
        title = { Text(title, color = Primary, fontSize = 20.sp) },
        actions = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu_1), contentDescription = "menu")
            }

        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
    )
}

@Composable
fun TopBarMenu(scope: CoroutineScope,scaffoldState: ScaffoldState,navController: NavController){
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
            title = { Text("Per??", color = Color.White, fontSize = 25.sp) },
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

@Composable
fun TopBarMenuDep(dep: String?, scope: CoroutineScope,scaffoldState: ScaffoldState,navController: NavController){
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
            title = {
                if (dep != null) {
                    Text(dep, color = Color.White, fontSize = 25.sp)
                }
            },
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

@Composable
fun modal(navController: NavController){
    Column(
        Modifier
            .background(color = Primary)
            .fillMaxHeight()
    ) {
        Text(text = "Per??", fontSize = 25.sp ,color= Color.White,
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
                    .height(70.dp)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate(
                            route = AppScreens.DetalleDepartamento.route + "/" + departamento.code
                        )
                    },
                    backgroundColor = PrimaryAlpha
                ) {
                    Image(painter = painterResource(id = departamento.imgUri), contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(modifier = Modifier
                        .background(PrimaryAlpha)
                        .fillMaxSize())
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



@Composable
fun BottomBarNavegation(id:Int,navController: NavController){

    val items = navList

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
                clip = true
            },
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEachIndexed{ index, item ->
            BottomNavigationItem(
                icon = {Image(imageVector = ImageVector.vectorResource(id = item.icon), contentDescription = "item")},
                selected = id == index,
                label = {Text(item.title, fontSize = 8.sp)},
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardLugarTuristico(
    departamentoTitle: String,
    destinoTitle: String,
    destinoDescription: String,
    destinoImage: String,
    destinoLatitud: String,
    destinoLongitud: String,
    destinoCategory: String,
    navController: NavController,
    viewModel: DataStoreViewModel,
    roomView: EditViewModel,
    roomDestinoView: DestinoEditViewModel,
    size: Int,
){
    var isLiked by remember{
        mutableStateOf(false)
    }



    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(0.1.dp, color = Color.LightGray),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable {
                val destino = SharedDestino(
                    departamentoTitle,
                    destinoTitle,
                    destinoDescription,
                    destinoImage,
                    destinoLatitud,
                    destinoLongitud,
                    destinoCategory
                )
                gotoDestino(destino, navController)
            }
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = destinoImage,
                    builder = {
                        placeholder(R.drawable.placeholder)

                    }
                ),
                contentDescription = "null",
                modifier = Modifier
                    .size(100.dp, 110.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(destinoTitle.capitalize(), fontSize = 15.sp, color = Primary,
                            modifier = Modifier.weight(3f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Image(
                                imageVector = ImageVector.vectorResource(if(isLiked) R.drawable.ic_heart else  R.drawable.ic_heart_unselected),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 10.dp)
                                    .clickable {
                                        isLiked = !isLiked
                                        roomView.onEvent(EditEvent.EnteredTitle(departamentoTitle))
                                        roomView.onEvent(EditEvent.InsertDepartamento)
                                        val sizeCurrent = size + 1
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredTitle(destinoTitle))
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredDescription(destinoDescription))
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredImage(destinoImage))
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredCategory(destinoCategory))
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredLatitud(destinoLatitud))
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredLongitud(destinoLongitud))
                                        roomDestinoView.onEvent(DestinoEditEvent.EnteredDepID(sizeCurrent.toString()))
                                        roomDestinoView.onEvent(DestinoEditEvent.InsertDestino)
                                    }
                            )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "location")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(departamentoTitle, color = TextAlt, fontSize = 12.sp)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_star), contentDescription = "calification")
                            Text(text = "4.5")
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CardLugarTuristicoFavorito(
    departamentoTitle: String,
    destinoTitle: String,
    destinoDescription: String,
    destinoImage: String,
    destinoLatitud: String,
    destinoLongitud: String,
    destinoCategory: String,
    navController: NavController,
){

    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(0.1.dp, color = Color.LightGray),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable {
                val destino = SharedDestino(
                    departamentoTitle,
                    destinoTitle,
                    destinoDescription,
                    destinoImage,
                    destinoLatitud,
                    destinoLongitud,
                    destinoCategory
                )
                gotoDestino(destino, navController)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = destinoImage,
                    builder = {
                        placeholder(R.drawable.placeholder)

                    }
                ),
                contentDescription = "null",
                modifier = Modifier
                    .size(100.dp, 110.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(destinoTitle.capitalize(), fontSize = 15.sp, color = Primary,
                            modifier = Modifier.weight(3f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "location")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(departamentoTitle, color = TextAlt, fontSize = 12.sp)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_star), contentDescription = "calification")
                            Text(text = "4.5")
                        }
                    }
                }
            }
        }
    }
}

fun gotoDestino(destino: SharedDestino, navController: NavController){
    navController.currentBackStackEntry?.arguments?.putParcelable("shared_destino", destino)
    navController.navigate(AppScreens.DetalleLugarTuristico.route)
}

@Composable
fun destinoCategoria(dep:String,title: String, imgUri: Int, codeDep: String?, code: String?, navController: NavController){
    var isLiked by remember{
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(0.1.dp, color = Color.LightGray),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable {
                navController.navigate(AppScreens.DetalleLugarTuristico.route + "/${codeDep}/${code}")
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(imgUri),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp, 110.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(title, fontSize = 15.sp, color = Primary,
                            modifier = Modifier.weight(3f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(if(isLiked) R.drawable.ic_heart else  R.drawable.ic_heart_unselected),
                            contentDescription = "favorite",
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 10.dp)
                                .clickable {
                                    isLiked = !isLiked
                                }

                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "location")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(dep, color = TextAlt, fontSize = 12.sp)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(imageVector = ImageVector.vectorResource(R.drawable.ic_star), contentDescription = "calification")
                        Text(text = "4.5")

                    }
                }
            }
        }
    }
}
