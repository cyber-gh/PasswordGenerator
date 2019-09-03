package com.skyIT.passwordgenerator.gui

import android.content.Context
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.skyIT.passwordgenerator.data.AppDatabase
import com.skyIT.passwordgenerator.data.GeneratedPassword
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class HistoryViewModel : ViewModel() {
    private lateinit var database: AppDatabase

    fun insantiatiateDatabase(context: Context) {
        database = AppDatabase(context)
    }
//
    fun sendTestEntry() {
    tst = 5
//        GlobalScope.launch {
//
//            database.passwordDao().insertAll(
//                GeneratedPassword(
//                    "test",
//                    "daotest",
//                    "test"
//                )
//            )
//        }
    }
//
//    fun getAllPasswords() : List<GeneratedPassword> {
//        val data = database.passwordDao().getAll()
//        return data
//
//
//    }

    var tst = 0
}
