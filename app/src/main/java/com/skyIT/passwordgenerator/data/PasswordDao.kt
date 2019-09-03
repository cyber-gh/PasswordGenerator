package com.skyIT.passwordgenerator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PasswordDao {

    @Query("SELECT * FROM passwords")
    fun getAll() : List<GeneratedPassword>

    @Insert
    fun insertAll(vararg pass:GeneratedPassword)
}