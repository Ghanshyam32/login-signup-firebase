package com.ghanshyam.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ghanshyam.firebaseproject.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            signUpBtn.setOnClickListener {
                val email: String = signupEmail.text.toString()
                val password: String = signUpPassword.text.toString()
                val confirmPassword: String = signUpCfmPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                    if (password == confirmPassword) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent =
                                        Intent(applicationContext, DashboardActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        it.exception.toString(),
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Password should be same",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Some fields are empty", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }
    }
}