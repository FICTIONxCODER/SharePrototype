package com.bea.shareprototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bea.shareprototype.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fileName = "prototypeTest.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val config1: MutableMap<String, String> = HashMap()
        config1["component1"] = "url1"
        config1["component2"] = "url1"
        config1["component3"] = "url1"

        val config2: MutableMap<String, String> = HashMap()
        config2["component1"] = "url1"
        config2["component2"] = "url1"
        config2["component3"] = "url1"

        val map: MutableMap<String, Map<String, String>> = HashMap()
        map["config1"] = config1
        map["config2"] = config2

        val data = Data(map)

        val gson = Gson()
        val json = gson.toJson(data)

        binding.BtnShare.setOnClickListener {
            // Create a path where we will place our private file on external storage.
            val file = File(getExternalFilesDir(null), fileName)
            file.createNewFile()

            if (file.exists()) {
                val fo: OutputStream = FileOutputStream(file)
                fo.write(json.toByteArray())
                fo.close()
                println("file created: $file")
            }

            val data = gson.fromJson(json, Data::class.java)
            val url = data.map!!["config1"]!!["component1"]


        }
    }


}