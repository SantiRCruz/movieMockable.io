package com.example.movietechnicaltest.data.models.movies

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse(

    @field:SerializedName("actors")
    val actors: List<Person>,

    @field:SerializedName("classification")
    val classification: Classification,

    @field:SerializedName("directed_by")
    val directedBy: List<Person>,

    @field:SerializedName("duration")
    val duration: String,

    @field:SerializedName("gender")
    val gender: List<Gender>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("movie_images")
    val movieImages: MovieImages,

    @field:SerializedName("popularity_qualification")
    val popularityQualification: Int,

    @field:SerializedName("release_year")
    val releaseYear: String,

    @field:SerializedName("synopsis")
    val synopsis: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("type")
    val type: Type
): Parcelable