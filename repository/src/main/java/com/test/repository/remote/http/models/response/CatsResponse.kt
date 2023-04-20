package com.test.repository.remote.http.models.response

import com.google.gson.annotations.SerializedName
import com.test.entities.Cat
import com.test.repository.BuildConfig

data class CatsResponse(
    val id: String,
    @SerializedName("name")
    val breedName: String?,
    val origin: String,
    val affectionLevel: Int,
    val intelligence: Int,
    @SerializedName("reference_image_id")
    val imageId: String?,
) {
    fun toCat(): Cat {
        return Cat(
            id = this.id,
            breedName = this.breedName,
            origin = this.origin,
            affectionLevel = this.affectionLevel,
            intelligence = this.intelligence,
            imageUrl = if (!this.imageId.isNullOrEmpty()) "${BuildConfig.CDN_URL}${this.imageId}.jpg" else null
        )
    }
}

fun List<CatsResponse>.toCatList(): List<Cat> = this.map {
    it.toCat()
}
