package com.idnp.danp_proyecto_final.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.idnp.danp_proyecto_final.data.models.Departamento
import kotlinx.coroutines.tasks.await

class FirestorePagingSource (
    private val queryDepartamentos: Query
): PagingSource<QuerySnapshot, Departamento>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, Departamento>): QuerySnapshot? = null

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Departamento> {
        return try{
            val currentPage = params.key ?: queryDepartamentos.get().await()
            val lastVisibleDepartamento = currentPage.documents[currentPage.size() -1 ]
            val nextPage = queryDepartamentos.startAfter(lastVisibleDepartamento).get().await()
            LoadResult.Page(
                data = currentPage.toObjects(Departamento::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}