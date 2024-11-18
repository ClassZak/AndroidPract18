package com.example.androidpract18

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainLandscapeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_landscape)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            startActivity(Intent(this,MainPortraitActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))

        val spinner: Spinner?= findViewById<Spinner>(R.id.speciesSpinner)
        if (spinner!=null){
            val newAdapter=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedItem = parent?.getItemAtPosition(position).toString()
                        val spinnerRef=(view as Spinner)
                        if (position==0)
                            return
                        else{
                            if(spinnerRef.adapter.count==4) {
                                deleteFirstButton()
                            }
                        }
                        val manyElemnts:Boolean=spinnerRef.adapter.count==4
                        setTextForSpecies(position-if(manyElemnts) 1; else 0)
                        setSpeciesImage(position-if(manyElemnts) 1; else 0)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            spinner.onItemSelectedListener=newAdapter

            val choosesList=mutableListOf("Выберете породу")
            choosesList.addAll(resources.getStringArray(R.array.SpeciesLocaled))
            val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, choosesList)
            spinner.adapter=adapter
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT)
            startActivity(Intent(this,MainPortraitActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
    }
    fun deleteFirstButton(){
        findViewById<Spinner>(R.id.speciesSpinner).adapter=android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.SpeciesLocaled))
    }

    val speciesArrayTable=arrayOf(
        R.array.Doberman,
        R.array.KotonDeTulear,
        R.array.Bladhaund
    )
    val imageTable= arrayOf(
        R.drawable.doberman,
        R.drawable.bloodhound,
        R.drawable.kotondeulear
    )

    fun setTextForSpecies(number:Int):Unit{
        findViewById<TextView>(R.id.speciesName).setText(resources.getStringArray(R.array.SpeciesLocaled)[number])
        val editTextsArray=arrayOf(
            R.id.SpeciesInfo1,
            R.id.SpeciesInfo2,
            R.id.SpeciesInfo3,
            R.id.SpeciesInfo4,
            R.id.SpeciesInfo5,
            R.id.SpeciesInfo6,
            R.id.SpeciesInfo7,
            R.id.SpeciesInfo8
        )
        val stringArray= resources.getStringArray(speciesArrayTable[number])
        for(i in 0..7) {
            findViewById<TextView>(editTextsArray[i]).setText(stringArray[i])
        }
    }
    fun setSpeciesImage(number: Int):Unit{
        findViewById<ImageView>(R.id.speciesImage).setImageResource(imageTable[number])
    }
}