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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.android.gms.tasks.Continuation
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.room.presentation.edit.EditViewModel

@Composable
fun departamentoImage(
    image: Uri,
    onImageChange: (Uri) -> Unit,
) {

    var mStorage: StorageReference
    var selectedImage by remember { mutableStateOf<Uri?>(null) }


    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        selectedImage = uri
    }

    ContentDepartamentoImage(image, selectedImage) {
        launcher.launch("image/*")
    }
    mStorage = FirebaseStorage.getInstance().getReference()
    val filePath: StorageReference = mStorage.child("fotos").child(selectedImage.toString().substringAfterLast("/"))
    val image = selectedImage?.let {
        filePath.putFile(it)
    }
    val urlImage = image?.continueWithTask { img ->
        if(!img.isSuccessful){
            img.exception?.let{
                throw  it
            }
        }
        filePath.downloadUrl
    }?.addOnCompleteListener{ img ->
        if(img.isSuccessful){
            val downloadUri = img.result
            onImageChange(downloadUri)
            Log.d("IMAGE", ">>" + downloadUri)
        }
    }
}
@Composable
private  fun ContentDepartamentoImage(
    image: Uri,
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
                Image(painter = rememberImagePainter(
                    data = if(selectedImage != null) selectedImage else {if(image !=null) image else "none"},
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
    //departamentoImage("arequipa", onImageChange = {})
}