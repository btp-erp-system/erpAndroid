package com.example.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
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
    private lateinit var signupProgressBarIcon: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etEmail = findViewById(R.id.emailText)
        etPassword = findViewById(R.id.passText)
        etUserName = findViewById(R.id.usernameText)
        signupProgressBarIcon = findViewById(R.id.signupLoadingIcon)
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
                auth.createUserWithEmailAndPassword(etEmail.text.toString().trim(),etPassword.text.toString().trim()).
                addOnCompleteListener {task ->
                    if(task.isComplete){
                        val newUser = User(etUserName.text.toString().trim(),etEmail.text.toString().trim(),etPassword.text.toString().trim())
                    }
                }
                showSignupLoadingIcon()
                simulateSIgnup()
            }
        }
    }

    private fun showSignupLoadingIcon(){
        signupProgressBarIcon.visibility = View.VISIBLE
    }

    private fun hideSignupLoadingIcon(){
        signupProgressBarIcon.visibility = View.GONE
    }

    private fun simulateSIgnup(){
        Handler(Looper.getMainLooper()).postDelayed({
            hideSignupLoadingIcon()
            Toast.makeText(this, "Registration Done", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        },3000)
    }
}