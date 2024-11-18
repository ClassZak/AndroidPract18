package com.example.androidpract18

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainPortraitActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_portrait)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner?= findViewById<Spinner>(R.id.speciesSpinner)
        if (spinner!=null){
            val newAdapter=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedItem = parent?.getItemAtPosition(position).toString()
                        if (position==0)
                            return
                        startDetailActivity(position-1)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            spinner.onItemSelectedListener=newAdapter

            val chosesList=mutableListOf("Выберете породу")
            chosesList.addAll(resources.getStringArray(R.array.SpeciesLocaled))
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, chosesList)
            spinner.adapter=adapter
        }
    }

    fun startDetailActivity(number:Int){
        startActivity(Intent(this,DetailPortraitActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY).putExtra("selectedNumber",number))
    }
}