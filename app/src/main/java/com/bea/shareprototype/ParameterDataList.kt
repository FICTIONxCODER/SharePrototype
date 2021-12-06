package com.bea.shareprototype

import com.google.gson.annotations.SerializedName
//  Created by BEA on 2021.
//  Copyright © 2021 BEA. All rights reserved.
data class ParameterDataList(
    @SerializedName("id") val id: String,
    @SerializedName("restore") val restore: String,
    @SerializedName("value") val value: String
)
