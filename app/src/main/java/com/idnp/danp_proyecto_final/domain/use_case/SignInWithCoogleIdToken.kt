package com.idnp.danp_proyecto_final.domain.use_case

import com.idnp.danp_proyecto_final.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class SignInWithGoogleIdToken(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Flow<Result<Unit>> {
        return repository.signInWithCredential(idToken)
    }
}

