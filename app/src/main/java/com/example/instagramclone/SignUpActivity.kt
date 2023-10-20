package com.example.instagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    //val binding by lazy
    private lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etEmail = findViewById(R.id.emailText)

        val btnSignup = findViewById<Button>(R.id.signupbtn)
        btnSignup.setOnClickListener {
            val emailPattern = ".*@iitp\\.ac\\.in"
            val email = etEmail.text.toString().trim()
            if(email.isEmpty()){
                etEmail.error = "Email is required"
                return@setOnClickListener
            }
            else if(!email.matches(emailPattern.toRegex())){
                etEmail.error = "Enter your college email"
                return@setOnClickListener
            }
            else {
                Toast.makeText(this, "Registration Done", Toast.LENGTH_SHORT).show()
            }
        }
    }
}