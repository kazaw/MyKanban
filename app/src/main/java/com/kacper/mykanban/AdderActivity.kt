package com.kacper.mykanban

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class AdderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adder)

        val spinner: Spinner = findViewById(R.id.spinner_colors)
        ArrayAdapter.createFromResource(
            this, R.array.array_colors, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        val buttonInsert: Button = findViewById(R.id.button_insert)
        val editTextName : EditText = findViewById(R.id.editText_name)
        val editTextDescription : EditText = findViewById(R.id.editText_description)
        buttonInsert.setOnClickListener {
            if(editTextName.text.isEmpty() || editTextDescription.text.isEmpty() ){
                val toast = Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                try {
                    finish()
                }
                catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}