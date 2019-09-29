package com.skyIT.passwordgenerator.gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.data.GeneratedPassword
import com.skyIT.passwordgenerator.gui.generic.BaseFragment
import kotlinx.android.synthetic.main.history_fragment.*
import kotlinx.android.synthetic.main.password_history_view.view.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

interface ListElementDataSetter<T> {
    fun bindData(el: T)
}


class HistoryAdapter(private var listData: ArrayList<GeneratedPassword>) : RecyclerView.Adapter<HistoryAdapter.PasswordHolder> () {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.PasswordHolder {
        return PasswordHolder(parent.inflate(R.layout.password_history_view, false))
    }

    override fun getItemCount() = listData.count()

    override fun onBindViewHolder(holder: HistoryAdapter.PasswordHolder, position: Int) {
        holder.bindData(listData[position])
    }

    class PasswordHolder(private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener, ListElementDataSetter<GeneratedPassword> {
        init {
            v.setOnClickListener(this)
        }

        var isHidden = true

        override fun onClick(p0: View?) {
            //nothing
        }

        fun getHiddenPass(normalPass: String) = normalPass.substring(startIndex = 0, endIndex = normalPass.length / 2) + "*".repeat(normalPass.length / 2)

        override fun bindData(el: GeneratedPassword) {
            v.password_el_pass.text = getHiddenPass(el.password)
            v.tap_to_revlea_btn.setOnClickListener {
                isHidden = !isHidden
                v.password_el_pass.text = if(isHidden) getHiddenPass(el.password) else el.password
            }

        }

    }

    fun updateList(newList : ArrayList<GeneratedPassword>) {
        listData = newList
        notifyDataSetChanged()
    }

}

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
