package com.example.umbrellahms

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReservationListAdapter(private val context: Activity, private val reservations: ArrayList<Reservation>, private val resIds : ArrayList<String>)
    : ArrayAdapter<Reservation>(context, R.layout.custom_list, reservations) {
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_res_list, null, true)
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Reservations")

        val res = rowView.findViewById(R.id.restxt) as TextView
        val cancel = rowView.findViewById(R.id.cancel_res) as Button

        res.text = "reservation from ${reservations.get(position).check_in} to ${reservations.get(position).check_out}"

        cancel.setOnClickListener{
            myRef.child(resIds.get(position)).removeValue()
        }

        return rowView
    }
}