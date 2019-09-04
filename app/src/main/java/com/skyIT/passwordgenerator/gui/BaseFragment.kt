package com.skyIT.passwordgenerator.gui

import android.os.Bundle
import androidx.fragment.app.Fragment
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
}