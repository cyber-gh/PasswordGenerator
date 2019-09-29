package com.skyIT.passwordgenerator.gui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.gui.generic.BaseFragment
import kotlinx.android.synthetic.main.history_fragment.*
import kotlinx.android.synthetic.main.password_history_view.view.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) : View{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

data class PasswordHistoryElement(val pass: String, val timeGenerated: String? = null)

class HistoryAdapter(private var listData: ArrayList<PasswordHistoryElement>) : RecyclerView.Adapter<HistoryAdapter.PasswordHolder> () {
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

    class PasswordHolder(private var v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(p0: View?) {
            //nothing
        }

        private var data: PasswordHistoryElement? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindData(data: PasswordHistoryElement) {
                v.password_el_pass.text = data.pass
        }

    }

}

class HistoryFragment : BaseFragment() {
    private lateinit var viewModel: HistoryViewModel

    private lateinit var listAdapter : HistoryAdapter

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
        initializeViewModel()
        bindUI()
    }


    private fun bindUI() {
        listAdapter = HistoryAdapter(arrayListOf(
            PasswordHistoryElement("Test 1", "never"),
            PasswordHistoryElement("Test 2", "never"),
            PasswordHistoryElement("Test 3", "never")
        ))
        password_list.layoutManager = LinearLayoutManager(context)
        password_list.adapter = listAdapter
    }
}
