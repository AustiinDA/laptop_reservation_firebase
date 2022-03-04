package com.iessanalberto.dam2.reservaportatiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {

    private lateinit var registroEMail: EditText
    private lateinit var passwordET1: EditText
    private lateinit var passwordET2: EditText
    private lateinit var btnReg: Button
    private val auth: FirebaseAuth = Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        registroEMail = findViewById(R.id.etEmail)
        passwordET1 = findViewById(R.id.etPass1)
        passwordET2 = findViewById(R.id.etPass2)
        btnReg = findViewById(R.id.reg_btn)

        btnReg.setOnClickListener {
            val email = registroEMail.text.toString()
            val password = passwordET1.text.toString()
            val password2 = passwordET2.text.toString()
            if (validar(email, password, password2)) {
                registrar(email, password)
            }
        }
    }

    private fun registrar(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "REGISTRO COMPLETADO", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, "Fallo al crear cuenta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validar(email: String, password: String, password2: String): Boolean {
        if (email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
            if (password.equals(password2)) {
                //ha ido guay
                if (email.substringAfterLast("@")
                        .equals("iessanalberto.com") && !email.substringAfterLast("@")
                        .isDigitsOnly()
                ) {
                    return true
                }
            } else {
                Toast.makeText(applicationContext, "No estás autorizado", Toast.LENGTH_SHORT).show()
                return false
            }
        } else {
            registroEMail.error = "Rellena el email"
            passwordET1.error = "Contraseña requerida"
            passwordET2.error = "Contraseña requerida"
            return false
        }
        return false
    }


}