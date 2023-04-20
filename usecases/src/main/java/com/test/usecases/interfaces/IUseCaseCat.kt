package com.test.usecases.interfaces

import com.test.entities.ObjectResult
import com.test.entities.Cat

interface IUseCaseCat {
    suspend fun getCats(): ObjectResult<List<Cat>>
}

