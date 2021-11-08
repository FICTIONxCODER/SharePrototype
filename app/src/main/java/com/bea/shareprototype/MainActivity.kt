package com.bea.shareprototype

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bea.shareprototype.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fileName = "prototypeTest.json"
    private var fileData = "binding.EditText.toString()"
    //val btnSave  = binding.BtnShare
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        

        binding.BtnShare.setOnClickListener {
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
                fileOutputStream.write(fileData.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data saved", Toast.LENGTH_LONG).show()


        }
    }


}