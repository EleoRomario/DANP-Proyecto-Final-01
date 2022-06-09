package com.idnp.danp_proyecto_final.room.presentation.edit.components

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.idnp.danp_proyecto_final.R

@Composable
fun departamentoImage(
    image: String,
    onImageChange: (String) -> Unit
) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    if(image != null)selectedImage=Uri.parse(image)

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        selectedImage = uri
    }

    ContentDepartamentoImage(selectedImage) {
        launcher.launch("image/*")
    }
    onImageChange(selectedImage.toString())
    Log.d("IMAGE", "selectedImage"+selectedImage+">image"+image)
}
@Composable
private  fun ContentDepartamentoImage(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            OutlinedButton(onClick = onImageClick) {
                Text(text = "Seleccionar Imagen")
            }
            if (selectedImage != null)
                Image(painter = rememberImagePainter(
                    data = selectedImage,
                    builder = {
                        placeholder(R.drawable.placeholder)

                    }
                ),
                    contentDescription = "selected image",
                    modifier = Modifier.size(60.dp)
                )
        }
}
@Preview
@Composable
fun PreviewdepartamentoImage() {
    departamentoImage("arequipa", onImageChange = {})
}