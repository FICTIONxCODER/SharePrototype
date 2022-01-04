package com.bea.widescan.ui.favorites

import com.fictionXcoder.shareprototype.ParameterDataList
import com.google.gson.annotations.SerializedName

//  Created by BEA on 2021.
//  Copyright © 2021 BEA. All rights reserved.


data class ReadJSON(
    @SerializedName("Company")
    val company: String,
    @SerializedName("FP_version")
    val fp_version: String,
    @SerializedName("Family")
    val family: String,
    @SerializedName("CAN_ID")
    val canID: String,
    @SerializedName("DT_UTC")
    val date: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("DD_localisation")
    val location: String,
    @SerializedName("parameters")
    val parameterItems: Array<ParameterDataList>
)