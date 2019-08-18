package com.skyIT.passwordgenerator.gui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.skyIT.passwordgenerator.R

class GeneratorFragment : Fragment() {

    companion object {
        fun newInstance() = GeneratorFragment()
    }

    private lateinit var viewModel: GeneratorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.generator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GeneratorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
