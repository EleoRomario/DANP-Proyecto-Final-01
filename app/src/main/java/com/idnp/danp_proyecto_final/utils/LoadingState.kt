package com.idnp.danp_proyecto_final.utils

data class LoadingState(
    val status: Status = Status.IDLE,
    val message: String? = null
){
    companion object{
        val LOADING = LoadingState(Status.LOADING)
        val SUCCESS = LoadingState(Status.SUCCESS)
        val IDLE = LoadingState(Status.IDLE)
        fun error(message: String) = LoadingState(Status.FAILED, message = message)
    }

    enum class Status {
        SUCCESS,
        LOADING,
        FAILED,
        IDLE
    }
}
