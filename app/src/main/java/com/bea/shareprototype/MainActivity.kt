package com.bea.shareprototype

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var fileReader:FileReader
    private lateinit var fileWriter:FileWriter
    private lateinit var bufferReader:BufferedReader
    private lateinit var bufferWriter:BufferedWriter
    private var response:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1);
        }



        var saveButton:View = findViewById<View>(R.id.BtnShare)
        var file:File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"myJSONfile")

        saveButton.setOnClickListener {
            if (!file.exists()) {
                try {
                    file.createNewFile()
                    var fileWriter = FileWriter(file.absoluteFile)
                    bufferWriter = BufferedWriter(fileWriter)
                    bufferWriter.write("{}")
                    bufferWriter.close()
                    Log.i("New File",file.name + file.absolutePath )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            Log.i("File Exist",file.name + file.absolutePath )
        }
    }
}