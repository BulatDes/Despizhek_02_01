package com.bignerdranch.android.despizhekpr31

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var regButton: Button
    lateinit var loginText: EditText
    lateinit var passText: EditText
    lateinit var shar:SharedPreferences
    lateinit var editor:Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        regButton = findViewById(R.id.regButton)
        loginText = findViewById(R.id.loginText)
        passText = findViewById(R.id.passText)
        shar = getSharedPreferences("MyShar", MODE_PRIVATE);
        editor = shar.edit()
        editor.clear()
        editor.commit()
        regButton.setOnClickListener{
            var login:String = loginText.text.toString()
            var pass:String = passText.text.toString()

            if(login.isEmpty() || pass.isEmpty()){
                Snackbar.make(it,"Введите логин и пароль",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val checkLogin:String = shar.getString("login","").toString()
            val checkPassword:String = shar.getString("password","").toString()

            if(checkLogin.isEmpty() && checkPassword.isEmpty()){
                editor.putString("login", login)
                editor.putString("password", pass)
                editor.commit()
                Snackbar.make(it,"Успешная регистрация",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(checkLogin != login && checkPassword != pass){
                Snackbar.make(it,"Неверный логин или пароль",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{
                intent = Intent(this,SecondActivity::class.java)
                startActivity(intent)
            }


        }


    }
}