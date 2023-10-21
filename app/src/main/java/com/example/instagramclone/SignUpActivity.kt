package com.example.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.instagramclone.Models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {

    //val binding by lazy
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etUserName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etEmail = findViewById(R.id.emailText)
        etPassword = findViewById(R.id.passText)
        etUserName = findViewById(R.id.usernameText)

        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database


        val btnSignup = findViewById<Button>(R.id.signupbtn)
        btnSignup.setOnClickListener {
            val emailPattern = ".*@iitp\\.ac\\.in"
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val userName = etUserName.text.toString().trim()
            if(email.isEmpty()){
                etEmail.error = "Email is required"
                return@setOnClickListener
            }
            else if(!email.matches(emailPattern.toRegex())){
                etEmail.error = "Enter your college email"
                return@setOnClickListener
            }
            else if(password.length<3){
                etPassword.error = "Password is to short"
            }
            else {
                Toast.makeText(this, "Registration Done", Toast.LENGTH_SHORT).show()
                auth.createUserWithEmailAndPassword(etEmail.text.toString().trim(),etPassword.text.toString().trim()).
                addOnCompleteListener {task ->
                    if(task.isComplete){
                        val newUser = User(etUserName.text.toString().trim(),etEmail.text.toString().trim(),etPassword.text.toString().trim())
                    }
                }
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
    }
}