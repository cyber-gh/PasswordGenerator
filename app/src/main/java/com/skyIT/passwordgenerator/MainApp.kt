package com.skyIT.passwordgenerator

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.skyIT.passwordgenerator.gui.generic.Router

class MyApplication : Application() {


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        Router.app = this
    }
}