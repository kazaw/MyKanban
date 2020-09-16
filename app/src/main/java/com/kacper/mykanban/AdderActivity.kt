package com.kacper.mykanban

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.utilities.*
import java.util.*


class AdderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_adder)

        val spinner: Spinner = findViewById(R.id.spinner_colors)
        ArrayAdapter.createFromResource(
            this, R.array.array_colors, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup_type)
        val buttonInsert: Button = findViewById(R.id.button_insert)
        val editTextName : EditText = findViewById(R.id.editText_name)
        val editTextDescription : EditText = findViewById(R.id.editText_description)

        fun fillFields(){
            val kanbanCard : KanbanCard = intent.getSerializableExtra("KanbanCardToEditExtra") as KanbanCard
            editTextName.setText(kanbanCard.name)
            editTextDescription.setText(kanbanCard.description)
            buttonInsert.text = getString(R.string.text_edit)
        }

        fun returnType(): String {
            val selectedID = radioGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(selectedID)

            return when (radioButton.text.toString()){
                getString(R.string.radiobutton_todo) -> TO_DO
                getString(R.string.radiobutton_in_progress) -> IN_PROGRESS
                getString(R.string.radiobutton_done) -> DONE
                else -> TO_DO
            }
        }

        fun returnColor(): Int {
            return when (spinner.selectedItem.toString()) {
                "Red" -> Color.RED
                "Green" -> Color.GREEN
                "Blue" -> Color.BLUE
                "Yellow" -> Color.YELLOW
                else -> Color.WHITE
            }
        }

        fun returnResult(){
            val returnIntent = Intent()
            val name = editTextName.text.toString()
            val description = editTextDescription.text.toString()
            val kanbanCard = KanbanCard(name, returnType() , description, returnColor(), Calendar.getInstance())
            returnIntent.putExtra("KanbanCardExtra", kanbanCard)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }

        fun returnEditedResult(){
            val returnIntent = Intent()
            val name = editTextName.text.toString()
            val description = editTextDescription.text.toString()
            val kanbanCard : KanbanCard = intent.getSerializableExtra("KanbanCardToEditExtra") as KanbanCard
            kanbanCard.name = name
            kanbanCard.type = returnType()
            kanbanCard.description = description
            kanbanCard.color = returnColor()
            returnIntent.putExtra("EditedKanbanCardExtra",kanbanCard)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }

        if(intent.extras?.getInt("requestCode") == REQUEST_EDIT){
            fillFields()
        }
        buttonInsert.setOnClickListener {
            if(editTextName.text.isEmpty() || editTextDescription.text.isEmpty() ){
                val toast = Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT)
                toast.show()
            }
            else{
                try {
                    when(intent.extras?.getInt("requestCode")){
                        REQUEST_INSERT -> returnResult()
                        REQUEST_EDIT -> returnEditedResult()
                        else -> throw Exception("AdderActivity Exception try")
                    }
                }
                catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}