package com.skyIT.passwordgenerator.gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.gui.generic.BaseFragment
import kotlinx.android.synthetic.main.history_fragment.*


class HistoryFragment : BaseFragment() {
    private lateinit var viewModel: HistoryViewModel

    private lateinit var listAdapter : HistoryAdapter

    override fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        viewModel.passwordDao = repository.productDao
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.history_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        bindUI()
    }


    private fun bindUI() {
        list_refresh_control.setOnRefreshListener {
            viewModel.getData()
        }

        listAdapter = HistoryAdapter(
            arrayListOf()
        )

        password_list.layoutManager = LinearLayoutManager(context)
        password_list.adapter = listAdapter

        viewModel.dataListLive.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                if (it.isEmpty())
                    Toast.makeText(context, "Containter is empty", Toast.LENGTH_LONG).show()

            }
            listAdapter.updateList(it)
            list_refresh_control.isRefreshing = false
        })


    }
}
