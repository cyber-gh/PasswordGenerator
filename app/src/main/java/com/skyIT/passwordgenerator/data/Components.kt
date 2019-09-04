package com.skyIT.passwordgenerator.data

import android.app.Application
import com.skyIT.passwordgenerator.gui.generic.BaseFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface FragmentComponent {

    fun inject(baseFragment: BaseFragment)

    val passwordDao: PasswordDao

    val database: AppDatabase

    val repository: Repository

    val application: Application
}