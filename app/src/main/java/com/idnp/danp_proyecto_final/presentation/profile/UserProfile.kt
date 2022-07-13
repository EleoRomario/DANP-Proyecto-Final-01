package com.idnp.danp_proyecto_final.presentation.profile

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.idnp.danp_proyecto_final.presentation.login.user.LoginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.datastore.FavoriteDestino
import com.idnp.danp_proyecto_final.domain.viewsmodel.DataStoreViewModel
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.components.CardLugarTuristico
import com.idnp.danp_proyecto_final.presentation.components.CardLugarTuristicoFavorito
import com.idnp.danp_proyecto_final.presentation.components.TopBarBack
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos
import com.idnp.danp_proyecto_final.room.presentation.edit.EditViewModel
import com.idnp.danp_proyecto_final.room.presentation.edit.destinos.DestinoEditViewModel
import com.idnp.danp_proyecto_final.room.presentation.home.HomeViewModel
import com.idnp.danp_proyecto_final.room.presentation.home.destinos.DestinoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

@Composable
fun ProfileScreen(
    navController: NavController,
    roomDepartamentos: HomeViewModel = hiltViewModel()
){
    val user = Firebase.auth.currentUser
    val state = roomDepartamentos.state.value

    Scaffold(
        topBar = {
            TopBarBack(navController)
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)){
                ProfileContent(navController, user,state.departamentos)
            }
        }
    )
}

@Composable
fun ProfileContent(
    navController: NavController,
    user: FirebaseUser?,
    departamentos: List<DepartamentoWithDestinos> = emptyList()
){
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(painter = rememberImagePainter(
            data = user?.photoUrl),
            contentDescription = "photo_url",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        )
        Text(text = user?.displayName.toString(), fontSize = 20.sp)
        Text(text = user?.email.toString(), color = Color.LightGray)
        OutlinedButton(
            onClick = {
                Firebase.auth.signOut()

                navController.navigate(route = AppScreens.LoginScreen.route){
                    navController.popBackStack()
                }
                Toast.makeText(context, "Successfully Signed Out", Toast.LENGTH_SHORT).show()
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Sign Out")
        }
        Divider(Modifier.fillMaxWidth())
        Text(text = "Destinos Favoritos")
        Spacer(modifier = Modifier.height(10.dp))
        Favoritos(
            navController,
            departamentos
        )
    }
}

@Composable
fun Favoritos(
    navController: NavController,
    departamentos: List<DepartamentoWithDestinos> = emptyList()
){
    //val favorites = viewModel.destinoPrefs.observeAsState().value
    Log.d("ROOM","->"+departamentos.size)
    for (destino in departamentos){
        Log.d("ROOM","->"+destino)
        LazyColumn(){
            items(destino.destinos){favorites->
                CardLugarTuristicoFavorito(
                    departamentoTitle = destino.departamento.title,
                    destinoTitle = favorites.title,
                    destinoDescription = favorites.description,
                    destinoImage = favorites.image,
                    destinoLatitud = favorites.latitud,
                    destinoLongitud = favorites.longitud,
                    destinoCategory = favorites.category,
                    navController = navController,
                )
                Log.d("FAVORITE","------"+favorites.title)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun preveProfile(){
    val viewModel = LoginViewModel()
    //ProfileScreen(viewModel)
}