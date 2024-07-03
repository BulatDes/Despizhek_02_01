package com.bignerdranch.android.despizhekpr31

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar
import kotlin.math.PI

class SecondActivity : AppCompatActivity() {
    lateinit var DataText:EditText
    lateinit var DataText2:EditText
    lateinit var Image:ImageView
    lateinit var calculateButton: Button
    lateinit var spinnerShape:Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        DataText = findViewById(R.id.dataText)
        DataText2 = findViewById(R.id.dataText2)
        Image = findViewById(R.id.imageShape)
        spinnerShape = findViewById(R.id.spinnerShape)
        calculateButton = findViewById(R.id.calculateButton)

        val items = resources.getStringArray(R.array.shapeArray)
        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,items){
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                if(position == 0){
                    view.visibility = View.GONE
                }
                else{
                    view.visibility = View.VISIBLE
                }
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerShape.adapter = adapter
        spinnerShape.setSelection(0,false)

        calculateButton.setOnClickListener{
            var result:Double = 0.0
            when(spinnerShape.selectedItem){
                "Выберите фигуру"->{
                    Snackbar.make(it,"Выберите фигуру",Snackbar.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                "Круг"->{
                    if(DataText.text.isEmpty()){
                        Snackbar.make(it,"Введите данные для расчета",Snackbar.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    val num = DataText.text.toString().toDoubleOrNull()
                    if(num == null){
                        Snackbar.make(it,"Неправильное значение",Snackbar.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    result = num/2 * PI



                }
                "Треугольник"->{
                    if(DataText.text.isEmpty() || DataText2.text.isEmpty()){
                        Snackbar.make(it,"Введите данные для расчета",Snackbar.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    val num = DataText.text.toString().toDoubleOrNull()
                    val num2 = DataText2.text.toString().toDoubleOrNull()
                    if(num == null || num2 == null){
                        Snackbar.make(it,"Неправильное значение",Snackbar.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                    result = 2*num+num2
                }

            }
            intent = Intent(this,ThirdActivity::class.java)
            intent.putExtra("result",result)
            intent.putExtra("shape",spinnerShape.selectedItem.toString())
            startActivity(intent)
        }


    }
}