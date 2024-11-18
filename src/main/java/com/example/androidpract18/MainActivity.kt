package com.example.androidpract18

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }


            val spinner:Spinner?=
            findViewById<Spinner>(R.id.speciesSpinner)
            if (spinner!=null){
                val newAdapter=
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedItem = parent?.getItemAtPosition(position).toString()
                        if (position==0)
                            return
                        setTextForSpecies(position-1)
                        setSpeciesImage(position-1)
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
        catch (e:Throwable){
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration){
        super.onConfigurationChanged(newConfig)
        Toast.makeText(this,if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT) "Портретная ориентация"; else "Альбомная ориентация",Toast.LENGTH_LONG).show()

        rotateActivity(newConfig.orientation)
    }
    fun rotateActivity(orientation: Int){
        val fragment1Ref= findViewById<View>(R.id.fragment)
        val fragment2Ref= findViewById<View>(R.id.fragment)
        //val imageRef=findViewById<ImageView>(R.id.speciesImage)
        val layoutRef=findViewById<LinearLayout>(R.id.MainLinearLayout)

        if(orientation==Configuration.ORIENTATION_PORTRAIT){
            layoutRef.orientation=LinearLayout.VERTICAL

            fragment1Ref.layoutParams.height=LayoutParams.WRAP_CONTENT
            fragment1Ref.layoutParams.width=LayoutParams.MATCH_PARENT

            fragment2Ref.layoutParams.height=LayoutParams.WRAP_CONTENT
            fragment2Ref.layoutParams.width=LayoutParams.MATCH_PARENT
        }
        else{
            layoutRef.layoutParams.width=resources.displayMetrics.widthPixels
            layoutRef.orientation=LinearLayout.HORIZONTAL

            fragment1Ref.layoutParams.height=LayoutParams.MATCH_PARENT
            fragment2Ref.layoutParams.height=LayoutParams.MATCH_PARENT
            //imageRef.layoutParams.height
            fragment1Ref.layoutParams.width=layoutRef.width/2
            fragment2Ref.layoutParams.width=layoutRef.width/2
        }
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