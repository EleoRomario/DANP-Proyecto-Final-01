package com.idnp.danp_proyecto_final.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithCredential(idToken: String): Flow<Result<Unit>>
    fun signOut()
}
