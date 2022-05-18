package com.idnp.danp_proyecto_final.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.ui.theme.TextAlt

/*
Rolando
* */

@Composable
fun DetalleLugarTuristicoScreen(){
    Scaffold {
        LugarBodyContent()
    }

}
@Composable
fun LugarBodyContent(){

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Image(painter = painterResource(id = R.drawable.bosqueneblinacarpish), contentDescription = "",
            contentScale  = ContentScale.Crop,
            modifier= Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
        )
        infoLugarTuristico()
    }

}

@Composable
fun infoLugarTuristico(){

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(text = "Lugar Turistico", fontSize = 25.sp)
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart), contentDescription = "heart",

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
            Text(text = "Ubicacion" , fontSize = 16.sp, color = TextAlt)
        }
        Text("Descripcion", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris pretium erat quis luctus vestibulum. In arcu mauris, porttitor sit amet gravida non, pulvinar vitae purus.", fontSize = 14.sp, color = TextAlt)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.height(400.dp)){
            mapLugarTuristico()
        }
    }
}

@Composable
fun mapLugarTuristico(){
    val marker = LatLng(-9.5158261,-76.0369947)
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
            Marker(position = marker, title = "Lugar", snippet = "Bonito lugar")
        }
    }


}

//@Preview(showSystemUi = true)
@Preview(showBackground = true)
@Composable
fun LugaresDefaultPreview() {
    DetalleLugarTuristicoScreen()
}