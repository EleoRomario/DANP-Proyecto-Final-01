package com.idnp.danp_proyecto_final.presentation.login.user

data class LoadingState constructor(
    val status: Status,
    val msg: String? = null,
    val successRegister: Boolean = false
) {
    companion object {
        val LOADED = LoadingState(Status.SUCCESS)
        val IDLE = LoadingState(Status.IDLE)
        val LOADING = LoadingState(Status.RUNNING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED,
        IDLE,
    }
}
