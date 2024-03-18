package com.ghanshyam.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ghanshyam.firebaseproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            loginBtn.setOnClickListener {
                val email: String = loginEmail.text.toString()
                val password: String = loginPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent =
                                    Intent(applicationContext, DashboardActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Login failed: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(applicationContext, "Some fields are empty", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
