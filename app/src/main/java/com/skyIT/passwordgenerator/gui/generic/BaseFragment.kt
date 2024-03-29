package com.skyIT.passwordgenerator.gui.generic

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.skyIT.passwordgenerator.data.AppModule
import com.skyIT.passwordgenerator.data.DaggerFragmentComponent
import com.skyIT.passwordgenerator.data.Repository
import com.skyIT.passwordgenerator.data.RoomModule
import javax.inject.Inject

abstract class BaseFragment : Fragment() {


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
        initializeViewModel()
    }

    abstract fun initializeViewModel()

    fun toastS(str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    fun toastL(str: String) = Toast.makeText(context, str, Toast.LENGTH_LONG).show()

}