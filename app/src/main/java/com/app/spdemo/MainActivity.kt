package com.app.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var nameText: EditText
    private lateinit var ageText: EditText
    private lateinit var nextButton: Button
    private lateinit var sharedPreferences: SharedPreferences // -> make the data remaining even after we close the apps
    private lateinit var editor: SharedPreferences.Editor

    private fun initComponent(){
        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)
        nextButton = findViewById(R.id.btnNext)
    }

    private fun initSharedPreferences(){
        sharedPreferences = getSharedPreferences("my_sf", MODE_PRIVATE) // -> my_sf is a name of folder in android.data in phone
        editor = sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initSharedPreferences()

    }

    override fun onPause() {  // -> safe the data in pause state
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        editor.apply {
            putString("sf_name", name)
            putInt("sf_age", age)
            commit() // -> must
        }
    }

    override fun onResume() {  // -> load the data that you save
        super.onResume()
        val name = sharedPreferences.getString("sf_name", null)
        val age = sharedPreferences.getInt("sf_age", 0)

        nameText.setText(name)

        when{
            age != 0 -> ageText.setText(age.toString())
        }

    }
}