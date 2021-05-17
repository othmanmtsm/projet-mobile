package com.example.umbrellahms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.HashMap

class ReservationList : AppCompatActivity() {
    lateinit var lv_reservations : ListView
    lateinit var reservations : ArrayList<Reservation>
    lateinit var ids : ArrayList<String>
    lateinit var adapter: ArrayAdapter<Reservation>

    lateinit var database: FirebaseDatabase
    lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)

        lv_reservations = findViewById(R.id.lv_reservations)!!

        reservations = ArrayList()
        ids = ArrayList()
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Reservations")

        myRef.addValueEventListener(object:ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                reservations.clear()
                for ( snapshot in snapshot.children){
                    var row : Map<String , Object> = snapshot.getValue() as HashMap<String, Object>
                    if (row.get("user_id").toString() == FirebaseAuth.getInstance().currentUser!!.uid){
                        println(row.get("user_id").toString())
                        var r = Reservation(row.get("user_id").toString(), row.get("room_id").toString(), row.get("check_in").toString(), row.get("check_out").toString())
                        reservations.add(r)
                        ids.add(snapshot.key.toString())
                    }
                }
                val adapter = ReservationListAdapter(this@ReservationList, reservations, ids)
                lv_reservations.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}