package com.bea.shareprototype

import android.R.string
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


//  Created by BEA on 2021.
//  Copyright © 2021 BEA. All rights reserved.

data class Data(var date: String) {

    private var jsonBody = JSONObject()
    var allItems: ArrayList<ParameterDataList> = ArrayList<ParameterDataList>()
    //private var parameters=mutableListOf<ParameterDataList>()
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

            val parameterList = ParameterDataList("U01","1","5")
            val parameterList2 = ParameterDataList("U02","0","2")
            allItems.add(parameterList2)
            allItems.add(parameterList)
            val gson :Gson = Gson()
            val parameterjson:String = gson.toJson(allItems)
            jsonBody.put("parameters",JSONObject(parameterjson))
            Log.i("JSON Object", parameterjson)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonBody
    }
}

