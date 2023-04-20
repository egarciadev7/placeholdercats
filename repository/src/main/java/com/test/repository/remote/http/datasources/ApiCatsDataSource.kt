package com.test.repository.remote.http.datasources

import com.test.entities.ObjectResult
import com.test.entities.Cat
import com.test.repository.remote.http.interfaces.IApiCatsDataSource
import com.test.repository.remote.http.models.response.toCatList
import com.test.repository.remote.http.services.CatService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiCatsDataSource(private val catService: CatService) : IApiCatsDataSource {
    override suspend fun getCats(): ObjectResult<List<Cat>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = catService.getCats()
                if (!response.isSuccessful) {
                    ObjectResult.Failure(Exception(response.errorBody().toString()))
                } else {
                    ObjectResult.Success(response.body()!!.toCatList())
                }
            } catch (e: Exception) {
                ObjectResult.Failure(e)
            }
        }
    }
}