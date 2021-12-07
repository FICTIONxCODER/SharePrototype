package com.bea.shareprototype

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var bufferWriter:BufferedWriter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var saveButton:View = findViewById(R.id.BtnSave)
        var readButton:View = findViewById(R.id.BtnRead)
        val dateFormatGmt = SimpleDateFormat("dd:MM:yyyyHH:mm:ss")
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"))
        var fileDate = dateFormatGmt.format(Date()).toString() + ""
        var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "WS_SensorCANNumber$fileDate.json"
        )

        saveButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1);
            }
            if (!file.exists()) {
                try {
                    file.createNewFile()
                    var fileWriter = FileWriter(file.absoluteFile)
                    bufferWriter = BufferedWriter(fileWriter)
                    bufferWriter.write(Data(fileDate).JSONData().toString())
                    bufferWriter.close()
                    Log.i("New File",file.name + file.absolutePath )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            Log.i("File Exist",file.name + file.absolutePath )
        }

        readButton.setOnClickListener {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1);
            }
            Log.i("Read Clicked","indise read click listner")
            val stream = FileInputStream(file)
            var jString: String?
            try {
                val fc: FileChannel = stream.channel
                val bb: MappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
                /* Instead of using default, pass in a decoder. */
                 jString = Charset.defaultCharset().decode(bb).toString()

                Log.i("Reading", jString)

                //Listing files
                val path = Environment.getExternalStorageDirectory().toString() + "/Download"
                Log.d("Files", "Path: $path")
                val directory = File(path)
                val files = directory.listFiles()
                // ArrayList of class ItemsViewModel
                val data = ArrayList<MyListData>()
                if(files != null)
                {
                    Log.d("Files", "Size: " + files.size)
                    for (i in files.indices) {
                        Log.d("Files", "FileName:" + files[i].name)
                        data.add(MyListData(R.drawable.ic_baseline_article_24, files[i].name))
                    }
                }
                else
                    Log.d("Files", "Files are not present")

                // getting the recyclerview by its id
                val recyclerview = findViewById<RecyclerView>(R.id.RecyclerViewFiles)

                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)



                /*// This loop will create 20 Views containing
                // the image with the count of view
                for (i in 1..20) {
                    data.add(MyListData(R.drawable.ic_baseline_article_24, "Item " + i))
                }*/

                // This will pass the ArrayList to our Adapter
                val adapter = MyListAdapter(this,data)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            } finally {
                stream.close()
            }
        }
    }

}