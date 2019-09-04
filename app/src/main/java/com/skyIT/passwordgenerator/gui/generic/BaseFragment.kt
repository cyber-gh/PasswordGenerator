package com.skyIT.passwordgenerator.gui.generic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import com.skyIT.passwordgenerator.data.AppModule
import com.skyIT.passwordgenerator.data.DaggerFragmentComponent
import com.skyIT.passwordgenerator.data.Repository
import com.skyIT.passwordgenerator.data.RoomModule
import javax.inject.Inject

open class BaseFragment : Fragment() {


    @Inject
    protected lateinit var repository: Repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFragmentComponent.builder().appModule(AppModule(activity!!.application)).roomModule(
            RoomModule(activity!!.application)
        ).build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun initializeViewModel() {

    }
}