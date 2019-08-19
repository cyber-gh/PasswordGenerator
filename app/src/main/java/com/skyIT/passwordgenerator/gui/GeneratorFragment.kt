package com.skyIT.passwordgenerator.gui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer

import com.skyIT.passwordgenerator.R
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams
import kotlinx.android.synthetic.main.generator_fragment.*



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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GeneratorViewModel::class.java)
        bindUI()
    }

    private fun bindUI() {
        viewModel.currentPasswordLive.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) generated_password.text = "Please select at least one option"
            else generated_password.text = it
        })
        include_symbols.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.includeSymbols = b
            viewModel.updatePassoword()
        }

        include_lower.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.inlcudeLower = b
            viewModel.updatePassoword()
        }
        include_upper.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.includeUpper = b
            viewModel.updatePassoword()
        }
        include_numbers.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.includeNumbers = b
            viewModel.updatePassoword()
        }

        generated_password.setOnClickListener {
            viewModel.updatePassoword()
        }

        copy_clib_btn.setOnClickListener {
            with(context!!) {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("password", viewModel.currentPasswordLive.value)
                clipboard.setPrimaryClip(clip)
            }

        }

        password_len_seek.onSeekChangeListener = (object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams?) {
                viewModel.passwordLen = seekParams?.progress ?: 0
                viewModel.updatePassoword()

            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {
                Log.w("test", "test")
            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {
                Log.w("test", "test")
            }

        })


    }

}
