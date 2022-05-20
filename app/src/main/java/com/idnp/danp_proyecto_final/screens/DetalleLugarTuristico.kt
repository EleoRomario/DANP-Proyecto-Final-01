package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.ui.theme.ColorTopBar
import com.idnp.danp_proyecto_final.ui.theme.TextAlt
import kotlinx.coroutines.launch

/*
Rolando
* */

@Composable
fun DetalleLugarTuristicoScreen(navController: NavController, departamento: String?, destino: String?){

    val dep= departamentosList.first {
        it.code == departamento
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController)},
        bottomBar = {
            BottomBarNavegation(-1,navController)
        },
    ){
        LugarBodyContent(departamento, destino)
        TopBarMenuDep(dep.title,scope,scaffoldState,navController)
    }


}
@Composable
fun LugarBodyContent(codeDep: String?, code: String?){

    val destinos = destinosList.filter{
        it.codeDep == codeDep
    }
    val depTitle = departamentosList.first{
        it.code == codeDep
    }
    val lugarT = destinos.first {
        it.code == code
    }

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Image(painter = painterResource(lugarT.img), contentDescription = "",
            contentScale  = ContentScale.Crop,
            modifier= Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
        )
        infoLugarTuristico(depTitle.title, lugarT.title,lugarT.description,lugarT.latitud,lugarT.longitud)

    }

}

@Composable
fun infoLugarTuristico(dep: String, title: String, des: String, lat: Double, long: Double){

    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(text = title,
                fontSize = 25.sp,
                modifier = Modifier.weight(4f)
            )
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_heart_unselected), contentDescription = "heart",
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_location), contentDescription = "location")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = dep , fontSize = 16.sp, color = TextAlt)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Descripción", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = des, fontSize = 14.sp, color = TextAlt)
        Spacer(modifier = Modifier.height(15.dp))
        Text("Localización", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.height(400.dp)){
            mapLugarTuristico(lat,long, title)
        }
    }
}

@Composable
fun mapLugarTuristico(lat: Double, long: Double, title: String){
    val marker = LatLng(lat,long)
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(marker, 10f)
    }
    Box () {
        GoogleMap(
            //modifier = Modifier.fillMaxSize(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(400.dp)
                .clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)),
            cameraPositionState = cameraPositionState
        ){
            Marker(position = marker, title = title, snippet = "Lugar Turistico")
        }
    }

}

//@Preview(showSystemUi = true)
@Preview(showBackground = true)
@Composable
fun LugaresDefaultPreview() {
    var navController= rememberNavController()
    DetalleLugarTuristicoScreen(navController,"arequipa", "are_04")
}