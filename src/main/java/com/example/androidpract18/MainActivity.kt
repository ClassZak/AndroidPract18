package com.example.androidpract18

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.E

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            findViewById<Spinner>(R.id.speciesSpinner).onItemSelectedListener= object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    setTextForSpecies(position)
                } // to close the onItemSelected

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
        catch (e:Exception){
            val s:Int=67
        }

    }


    val arrayTable=arrayOf(
        R.array.Doberman,
        R.array.KotonDeTulear,
        R.array.Bladhaund
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
        val stringArray= resources.getStringArray(arrayTable[number])
        for(i in 0..7) {
            findViewById<TextView>(editTextsArray[i]).setText(stringArray[i])
        }
    }
}