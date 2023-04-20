package com.test.usecases.usecases

import com.test.entities.ObjectResult
import com.test.entities.Cat
import com.test.repository.remote.http.interfaces.IApiCatsDataSource
import com.test.usecases.interfaces.IUseCaseCat
import java.lang.Exception

class UseCaseCat(
    private val apiCatsRemoteDataSource: IApiCatsDataSource,
) : IUseCaseCat {
    override suspend fun getCats(): ObjectResult<List<Cat>> {
        return try {
            val result = apiCatsRemoteDataSource.getCats()
            if (result is ObjectResult.Success) {
                ObjectResult.Success(result.data)
            } else {
                result
            }

        } catch (e: Exception) {
            ObjectResult.Failure(e)
        }
    }
}



