package com.example.umbrellahms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfirmRes : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference
    lateinit var confirmres : Button
    lateinit var roomtype : TextView
    lateinit var checkin : TextView
    lateinit var checkout : TextView
    lateinit var price : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_res)

        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Reservations")
        confirmres = findViewById(R.id.confres_btn)
        roomtype = findViewById(R.id.roomtype_txt)
        checkin = findViewById(R.id.checkin_txt)
        checkout = findViewById(R.id.checkout_txt)
        price = findViewById(R.id.price_txt)

        roomtype.text = "Room type : ${intent.getStringExtra("roomtype").toString()}"
        checkin.text = "Check in : ${intent.getStringExtra("check_in").toString()}"
        checkout.text = "Check out : ${intent.getStringExtra("check_out").toString()}"
        price.text = "Price per night : ${intent.getStringExtra("price").toString()} DH"

        var id : String = myRef.push().key as String
        confirmres.setOnClickListener {
            val res = Reservation(
                FirebaseAuth.getInstance().currentUser!!.uid,
                intent.getStringExtra("roomid").toString(),
                intent.getStringExtra("check_in").toString(),
                intent.getStringExtra("check_out").toString(),
            )
            myRef.child((id)).setValue(res).addOnSuccessListener {
                Toast.makeText(this@ConfirmRes, "Success", Toast.LENGTH_LONG).show()
                var intent = Intent(this@ConfirmRes, ReservationList::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}