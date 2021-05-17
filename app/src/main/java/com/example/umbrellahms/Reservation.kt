package com.example.umbrellahms

import java.util.*

class Reservation(
    var user_id : String,
    var room_id : String,
    var check_in : String,
    var check_out : String
) {
    override fun toString(): String {
        return "Reservation(user_id='$user_id', room_id='$room_id', check_in=$check_in, check_out=$check_out)"
    }
}