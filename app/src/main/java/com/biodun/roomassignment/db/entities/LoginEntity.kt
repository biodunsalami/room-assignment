package com.biodun.roomassignment.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var password: String

)