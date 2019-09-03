package com.skyIT.passwordgenerator.gui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.skyIT.passwordgenerator.R
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Router.app).create(HistoryViewModel::class.java)
        viewModel.insantiatiateDatabase(context!!)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onResume() {
        super.onResume()
        viewModel.tst = 2

//        viewModel.insantiatiateDatabase(context!!)
        viewModel.sendTestEntry()
//        viewModel.sendTestEntry()
//        viewModel.sendTestEntry()
//
//
//        placeholder.text = viewModel.getAllPasswords().joinToString(separator = "\n")

    }

}
