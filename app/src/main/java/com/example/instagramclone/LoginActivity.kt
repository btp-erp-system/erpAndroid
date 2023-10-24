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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var loginProgressBarIcon: ProgressBar
    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        if(currentUser!=null){
            startActivity(Intent(this,WelcomeActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.LoginEmailText)
        etPassword = findViewById(R.id.LoginPasswordText)
        auth = FirebaseAuth.getInstance()
        loginProgressBarIcon = findViewById(R.id.loginLoadingIcon)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        showLoginLoadingIcon()
                        simulateLogin()
                    }
                    else{
                        Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private fun showLoginLoadingIcon(){
        loginProgressBarIcon.visibility = View.VISIBLE
    }
    private fun hideLoginLoadingIcon(){
        loginProgressBarIcon.visibility = View.GONE
    }
    private fun simulateLogin(){
        Handler(Looper.getMainLooper()).postDelayed({
            hideLoginLoadingIcon()
            startActivity(Intent(this,WelcomeActivity::class.java))
            finish()
        },2000)
    }
}