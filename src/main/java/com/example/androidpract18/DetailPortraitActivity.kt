package com.example.androidpract18

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPortraitActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_portrait)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getSendedData(savedInstanceState)
        findViewById<Button>(R.id.backButton).setOnClickListener { view->
            startActivity(Intent(this,MainPortraitActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
        }
    }

    var selectedNumber:Int=0


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

    fun getSendedData(arguments:Bundle?){
        if (arguments!=null){
            try {
                selectedNumber=arguments.getInt("selectedNumber")
                setTextForSpecies(selectedNumber)
                setSpeciesImage(selectedNumber)
            }
            catch (e:Exception){
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }
}