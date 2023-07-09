package com.example.movietechnicaltest.data.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @field:SerializedName("code")
    val code: String = "",

    @field:SerializedName("status")
    val status: String = "",

    @field:SerializedName("amount_results")
    val amountResults: String = "",

    @field:SerializedName("results")
    val results: List<T> = listOf()
)
