package com.test.entities

import java.io.Serializable

data class Cat(
    val id: String,
    val breedName: String?,
    val origin: String,
    val affectionLevel: Int,
    val intelligence: Int,
    val imageUrl: String?,
) : Serializable

