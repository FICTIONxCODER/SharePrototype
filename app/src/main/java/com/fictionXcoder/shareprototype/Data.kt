package com.fictionXcoder.shareprototype

import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.util.*

data class Data(var date: String) {

    private var jsonBody = JSONObject()
    var allItems: ArrayList<ParameterDataList> = ArrayList<ParameterDataList>()
    //private var parameters=mutableListOf<ParameterDataList>()
    fun JSONData():JSONObject {
        try {
            jsonBody.put("Company", "fictionXcoder")
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
            jsonBody.put("parameters",parameterjson)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonBody
    }
}

