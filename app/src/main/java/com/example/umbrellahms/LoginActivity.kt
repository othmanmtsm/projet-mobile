package com.example.umbrellahms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    lateinit var register : TextView
    lateinit var login : Button
    lateinit var email : TextView
    lateinit var password : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register = findViewById(R.id.regtxt)
        login = findViewById(R.id.login_btn)
        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)

        login.setOnClickListener{
            when {
                TextUtils.isEmpty(email.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(password.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "please enter your password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email_txt : String = email.text.toString().trim{ it <= ' '}
                    val password_txt : String = password.text.toString().trim{ it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email_txt, password_txt).addOnCompleteListener(
                        OnCompleteListener <AuthResult> { task ->
                            if (task.isSuccessful) {
                                val firebaseuser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@LoginActivity,
                                    "You are logged in successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("user_email", email_txt)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }
        }

        register.setOnClickListener{
            var intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}