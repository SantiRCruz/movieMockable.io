package com.example.movietechnicaltest.data.models.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieImages(

    @field:SerializedName("frame_movie_path")
    val frameMoviePath: String,

    @field:SerializedName("poster_path")
    val posterPath: String
): Parcelable