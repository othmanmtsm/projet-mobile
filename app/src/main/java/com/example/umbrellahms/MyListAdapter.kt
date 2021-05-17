package com.example.umbrellahms

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class MyListAdapter (private val context: Activity, private val rooms: ArrayList<Room>, private val check_in : String, private val check_out : String)
: ArrayAdapter<Room>(context, R.layout.custom_list, rooms) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val roomType = rowView.findViewById(R.id.roomType) as TextView
        val description =  rowView.findViewById(R.id.description) as TextView
        val price =  rowView.findViewById(R.id.price) as TextView
        val roomImg = rowView.findViewById(R.id.roomImg) as ImageView
        val reservebtn = rowView.findViewById(R.id.reserve_btn) as Button

        Picasso.with(context).load(rooms.get(position).roomImg).into(roomImg)
        roomType.text = "${rooms.get(position).roomType} room"
        description.text = rooms.get(position).description
        price.text = "${rooms.get(position).price.toString()} DH"


        reservebtn.setOnClickListener {

            var intent = Intent(context, ConfirmRes::class.java)
            intent.putExtra("check_in", check_in)
            intent.putExtra("check_out", check_out)
            intent.putExtra("price", rooms.get(position).price.toString())
            intent.putExtra("roomid", rooms.get(position).id.toString())
            intent.putExtra("roomtype", rooms.get(position).roomType)
            context.startActivity(intent)
        }

        return rowView
    }
}