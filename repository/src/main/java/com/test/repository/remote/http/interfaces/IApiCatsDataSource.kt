package com.test.repository.remote.http.interfaces

import com.test.entities.ObjectResult
import com.test.entities.Cat

interface IApiCatsDataSource {
    suspend fun getCats(): ObjectResult<List<Cat>>
}
