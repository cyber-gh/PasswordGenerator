package com.skyIT.passwordgenerator.gui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.skyIT.passwordgenerator.data.GeneratedPassword
import com.skyIT.passwordgenerator.data.PasswordDao
import com.skyIT.passwordgenerator.gui.generic.BaseViewModel
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.util.*
import kotlin.math.absoluteValue

class GeneratorViewModel : BaseViewModel() {
    val currentPasswordLive = MutableLiveData<String>().apply { value = "generated-password" }
     lateinit var passwordDao: PasswordDao
    var passwordLen : Int = 16
    var includeSymbols: Boolean = false
    var includeNumbers : Boolean = false
    var inlcudeLower : Boolean = false
    var includeUpper : Boolean = false
    var excludeSimilar : Boolean = false

    fun updatePassoword(saveToDabase: Boolean = false) {
        viewModelScope.launch {


            val charSet = mutableListOf<Char>()
            if (includeNumbers) charSet += numberChars
            if (inlcudeLower) charSet += lowerChars
            if (includeUpper) charSet += upperChars
            if (includeSymbols) charSet += symbolCharset
            charSet.shuffle()
            if (charSet.isEmpty()) {
                currentPasswordLive.value = ""
                return@launch
            }
            var generatedPass: String = ""
            for (el in 0..passwordLen) {
                val randomNr = getTrueRandom().absoluteValue % charSet.size
                generatedPass += charSet[randomNr]
            }
            currentPasswordLive.value = generatedPass
            if (generatedPass.isNotEmpty() && saveToDabase) {
                bacgroundScope.launch {
                    passwordDao.insertAll(
                        GeneratedPassword(UUID.randomUUID().toString(), generatedPass, Calendar.getInstance().toString())
                    )
                }
            }

        }
    }

    fun getTrueRandom(): Int {
        val secureRandom = SecureRandom()
        secureRandom.setSeed(secureRandom.generateSeed(System.currentTimeMillis().toInt().absoluteValue % 1024))
        return secureRandom.nextInt()
    }
}

var lowerChars = mutableListOf<Char>().apply {
    for (el in 97..122) {
        this += el.toChar()
    }
}

var upperChars = mutableListOf<Char>().apply {
    for (el in 65..90) {
        this += el.toChar()
    }
}

var numberChars = mutableListOf<Char>().apply {
    for (el in 48..57) {
        this += el.toChar()
    }
}

var symbolCharset = mutableListOf<Char>().apply {
    for (el in 33..46) {
        this += el.toChar()
    }
}
