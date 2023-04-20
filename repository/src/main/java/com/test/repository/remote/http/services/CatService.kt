package com.test.repository.remote.http.services

import com.test.repository.remote.http.models.response.CatsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CatService {
    @GET("breeds")
    suspend fun getCats(): Response<List<CatsResponse>>
}
