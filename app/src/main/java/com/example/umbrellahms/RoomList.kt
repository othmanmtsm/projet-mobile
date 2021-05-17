package com.example.umbrellahms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class RoomList : AppCompatActivity() {
    lateinit var lv_rooms: ListView
    lateinit var rooms: ArrayList<Room>
    lateinit var adapter: ArrayAdapter<Room>

    lateinit var database: FirebaseDatabase
    lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)

        lv_rooms = findViewById(R.id.roomlist)

        rooms = ArrayList()
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Rooms")

        myRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for ( snapshot in snapshot.children){
                    var row : Map<String , Object> = snapshot.getValue() as HashMap<String , Object>
                    val p=Room(snapshot.key.toString().toInt(),row.get("roomImg").toString(), row.get("roomType").toString(),row.get("description").toString(),
                        row.get("price").toString().toFloat())
                    rooms.add(p)
                }
                val MyListAdapter = MyListAdapter(this@RoomList, rooms, intent.getStringExtra("check_in").toString(), intent.getStringExtra("check_out").toString())
                lv_rooms.adapter=MyListAdapter
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}