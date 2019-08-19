package com.skyIT.passwordgenerator.gui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class GeneratorViewModel : ViewModel() {
    val currentPasswordLive = MutableLiveData<String>().apply { value = "generated-password" }
    var passwordLen : Int = 16
    var includeSymbols: Boolean = false
    var includeNumbers : Boolean = false
    var inlcudeLower : Boolean = false
    var includeUpper : Boolean = false
    var excludeSimilar : Boolean = false

    fun updatePassoword() {
        val charSet = mutableListOf<Char>()
        if (includeNumbers) charSet += numberChars
        if (inlcudeLower) charSet += lowerChars
        if (includeUpper) charSet += upperChars
        if (includeSymbols) charSet += symbolCharset
        charSet.shuffle()
        if (charSet.isEmpty()) {
            currentPasswordLive.value = ""
            return
        }
        var generatedPass: String = ""
        for (el in 0..passwordLen) {
            generatedPass += charSet.random()
        }
        currentPasswordLive.value = generatedPass


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
