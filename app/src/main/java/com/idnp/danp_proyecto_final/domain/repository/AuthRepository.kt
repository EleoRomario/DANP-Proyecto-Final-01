package com.idnp.danp_proyecto_final.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    val currentUser = Firebase.auth.currentUser

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    suspend fun signInWithCredential(
        credential: AuthCredential,
        onComplete: (Boolean) -> Unit
    ) = withContext(Dispatchers.IO){
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    onComplete.invoke(true)
                }else{
                    onComplete.invoke(false)
                }
            }
    }

    fun signOut() = Firebase.auth.signOut()
}