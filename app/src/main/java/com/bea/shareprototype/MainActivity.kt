package com.bea.shareprototype

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

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

        var saveButton:View = findViewById(R.id.BtnSave)
        var readButton:View = findViewById(R.id.BtnRead)
        var ReadTextView:TextView = findViewById(R.id.ReadText)
        val dateFormatGmt = SimpleDateFormat("dd:MM:yyyyHH:mm:ss")
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"))
        var fileDate = dateFormatGmt.format(Date()).toString() + ""
        var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "WS_SensorCANNumber$fileDate.json"
        )

        saveButton.setOnClickListener {
            if (!file.exists()) {
                try {
                    file.createNewFile()
                    var fileWriter = FileWriter(file.absoluteFile)
                    bufferWriter = BufferedWriter(fileWriter)
                    bufferWriter.write("{Hello world}")
                    bufferWriter.close()
                    Log.i("New File",file.name + file.absolutePath )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            Log.i("File Exist",file.name + file.absolutePath )
        }

        readButton.setOnClickListener {
            var output:StringBuffer = StringBuffer()
            fileReader = FileReader(file.absoluteFile)
            bufferReader = BufferedReader(fileReader)
            var line:String = ""
            while ((bufferReader.readLine()) != null){
                output.append(line+"\n")
                }
            response = output.toString()
            Log.i("Read",response!!)
            ReadTextView.text = response!!
            bufferReader.close()
        }
    }
}