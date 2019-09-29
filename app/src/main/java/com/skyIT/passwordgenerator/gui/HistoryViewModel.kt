package com.skyIT.passwordgenerator.gui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.skyIT.passwordgenerator.data.AppDatabase
import com.skyIT.passwordgenerator.data.GeneratedPassword
import com.skyIT.passwordgenerator.data.PasswordDao
import com.skyIT.passwordgenerator.gui.generic.BaseViewModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class HistoryViewModel : BaseViewModel() {

    lateinit var passwordDao: PasswordDao
    var dataListLive = MutableLiveData<ArrayList<GeneratedPassword>>().apply { value =   arrayListOf<GeneratedPassword>() }



    fun getData() {
        bacgroundScope.launch {
            val ls =  passwordDao.getAll()
            dataListLive.postValue(ArrayList(ls))
        }
    }

}
