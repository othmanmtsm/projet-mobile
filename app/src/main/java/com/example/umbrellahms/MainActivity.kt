package com.example.umbrellahms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var srch : Button
    lateinit var logout : Button
    lateinit var myres : Button
    lateinit var check_in : TextView
    lateinit var check_out : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = intent.getStringExtra("user_id")
        val userEmail = intent.getStringExtra("user_email")
        srch = findViewById(R.id.search)
        check_in = findViewById(R.id.checkin_date)
        check_out = findViewById(R.id.checkout_date)
        logout = findViewById(R.id.btn_logout)
        myres = findViewById(R.id.myresbtn)


        srch.setOnClickListener{
            var intent = Intent(applicationContext, RoomList::class.java)
            intent.putExtra("check_in", check_in.text.toString())
            intent.putExtra("check_out", check_out.text.toString())
            startActivity(intent)
        }

        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            var intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        myres.setOnClickListener {
            var intent = Intent(applicationContext, ReservationList::class.java)
            startActivity(intent)
        }
    }
}