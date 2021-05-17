package com.example.umbrellahms

import android.icu.text.DateIntervalFormat
import java.util.*

class Room(
    var id : Int,
    var roomImg : String,
    var roomType : String,
    var description : String,
    var price : Float
) {
    override fun toString(): String {
        return "Room(id=$id, roomImg='$roomImg', roomType='$roomType', description='$description', price=$price)"
    }
}