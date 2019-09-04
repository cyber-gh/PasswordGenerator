package com.skyIT.passwordgenerator.gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.gui.generic.BaseFragment

class HistoryFragment : BaseFragment() {
    private lateinit var viewModel: HistoryViewModel

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.history_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
