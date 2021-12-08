package com.fictionXcoder.shareprototype

import com.google.gson.annotations.SerializedName

data class ParameterDataList(
    @SerializedName("id") val id: String,
    @SerializedName("restore") val restore: String,
    @SerializedName("value") val value: String
)
