package com.skyIT.passwordgenerator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "passwords")
data class GeneratedPassword(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "password")       val password: String,
    @ColumnInfo(name = "time_generated") val time: String
)