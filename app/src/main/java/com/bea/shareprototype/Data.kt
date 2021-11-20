package com.bea.shareprototype

import android.provider.DocumentsContract.createDocument
import android.util.Log
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat

//  Created by BEA on 2021.
//  Copyright © 2021 BEA. All rights reserved.

data class Data(var date: String) {

    private var jsonBody = JSONObject()
    private var parameters: JSONObject = JSONObject()
    fun JSONData():JSONObject {
        try {
            jsonBody.put("Company", "BEA")
            jsonBody.put("FP_version", 1.30)
            jsonBody.put("Family", "WS_")
            jsonBody.put("CAN_ID", 123456789)
            jsonBody.put("DT_UTC", date)
            jsonBody.put("user", 1)
            jsonBody.put("unit", 1)
            jsonBody.put("DD_localisation", "41.4033,02.17403")

            parameters.put("id","U01" )
            parameters.put("restore","1")
            parameters.put("value","5")

            jsonBody.put("parameters", parameters)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonBody
    }
}

