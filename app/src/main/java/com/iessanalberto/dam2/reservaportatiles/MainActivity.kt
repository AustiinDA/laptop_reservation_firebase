package com.iessanalberto.dam2.reservaportatiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    lateinit var loginEmail: EditText
    lateinit var loginPass: EditText
    private lateinit var loginBtn: Button
    private lateinit var regBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginEmail = findViewById<EditText>(R.id.loginEmail)
        loginPass = findViewById<EditText>(R.id.loginPassword)
        loginBtn = findViewById<Button>(R.id.btnLogin)
        regBtn = findViewById<Button>(R.id.btnRegistrar)
        //Boton login
        loginBtn.setOnClickListener{
            val email = loginEmail.text.toString()
            val password = loginPass.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            }else{
                Toast.makeText(applicationContext, "Rellena los campos", Toast.LENGTH_SHORT).show()
            }
        }
        //Registro
        regBtn.setOnClickListener{
            startActivity(Intent(this,Registro::class.java))
            finish()
        }
    }

    private fun login(email: String, password: String){
        val auth: FirebaseAuth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task->
            if (task.isSuccessful){
                startActivity(Intent(this,ReservaRecursos::class.java))
                finish()
            }else {
                Toast.makeText(applicationContext, "fallo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}