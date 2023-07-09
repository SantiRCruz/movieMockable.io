package com.example.movietechnicaltest.data.models.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Classification(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
): Parcelable