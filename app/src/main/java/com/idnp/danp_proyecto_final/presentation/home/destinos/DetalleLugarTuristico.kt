package com.idnp.danp_proyecto_final.presentation.home.destinos

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.CollectionReference
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.departamentosList
import com.idnp.danp_proyecto_final.data.destinosList
import com.idnp.danp_proyecto_final.domain.model.SharedDestino
import com.idnp.danp_proyecto_final.domain.viewsmodel.SharedViewModel
import com.idnp.danp_proyecto_final.presentation.components.BottomBarNavegation
import com.idnp.danp_proyecto_final.presentation.components.TopBarMenuDep
import com.idnp.danp_proyecto_final.presentation.components.modal
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentoListState
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentosViewModel
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DestinoListState
import com.idnp.danp_proyecto_final.ui.theme.TextAlt

@Composable
fun DetalleLugarTuristicoScreen(
    destino: SharedDestino,
    navController: NavController,
    viewModel: DepartamentosViewModel = hiltViewModel(),
){


    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val stateDestino = viewModel.stateD.value

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { modal(navController) },
        bottomBar = {
            BottomBarNavegation(-1,navController)
        },
    ){
        if (destino != null) {
            LugarBodyContent(
                destino.departamento,
                destino.title,
                destino.description,
                destino.image,
                destino.latitud,
                destino.longitud,
            )
            TopBarMenuDep(destino.departamento,scope,scaffoldState,navController)
        }
    }


}
@Composable
fun LugarBodyContent(
    departamentoTitle: String?,
    destinoTitle: String?,
    destinoDescription: String?,
    destinoImage: String?,
    destinoLatitud: String?,
    destinoLongitud: String?,
){

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Image(
            painter = rememberImagePainter(
                data = destinoImage,
                builder = {
                    placeholder(R.drawable.placeholder)

                }
            ),
            contentDescription = "null",
            modifier= Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)),
            contentScale = ContentScale.Crop
        )
        InfoLugarTuristico(
            departamentoTitle,
            destinoTitle,
            destinoDescription,
            destinoLatitud,
            destinoLongitud,
        )

    }

}

@Composable
fun InfoLugarTuristico(
    departamentoTitle: String?,
    destinoTitle: String?,
    destinoDescription: String?,
    destinoLatitud: String?,
    destinoLongitud: String?,
){

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
            if (destinoTitle != null) {
                Text(text = destinoTitle,
                    fontSize = 25.sp,
                    modifier = Modifier.weight(4f)
                )
            }
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
            if (departamentoTitle != null) {
                Text(text = departamentoTitle, fontSize = 16.sp, color = TextAlt)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Descripción", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        if (destinoDescription != null) {
            Text(text = destinoDescription, fontSize = 14.sp, color = TextAlt)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("Localización", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.height(400.dp)){
            if (destinoTitle != null) {
                if (destinoLongitud != null) {
                    if (destinoLatitud != null) {
                        mapLugarTuristico(destinoLatitud.toDouble(),destinoLongitud.toDouble(), destinoTitle)
                    }
                }
            }
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
    //DetalleLugarTuristicoScreen(navController,"arequipa", "are_04")
}