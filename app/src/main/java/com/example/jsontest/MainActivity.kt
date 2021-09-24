package com.example.jsontest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var verinfo:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verinfo = findViewById(R.id.verinfo_btn)

        var datos:List<Producto>
        verinfo.setOnClickListener(
            View.OnClickListener {
                datos=getProducts(this)
                Toast.makeText(this,""+datos[0].category,Toast.LENGTH_LONG).show()



            }
        )

    }

    private fun getJsonDataFromAsset(context: Context, fileName: String = "practica.json"): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getProducts(context: Context): List<Producto> {
        val jsonString = getJsonDataFromAsset(context)
        val listProductType = object : TypeToken<List<Producto>>() {}.type
        return Gson().fromJson(jsonString, listProductType)
    }
}