package com.skyIT.passwordgenerator.gui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.data.GeneratedPassword
import com.skyIT.passwordgenerator.utls.ListElementDataSetter
import com.skyIT.passwordgenerator.utls.inflate
import kotlinx.android.synthetic.main.password_history_view.view.*

class HistoryAdapter(private var listData: ArrayList<GeneratedPassword>) : RecyclerView.Adapter<HistoryAdapter.PasswordHolder>() {
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

    class PasswordHolder(private var v: View) : RecyclerView.ViewHolder(v),
        View.OnClickListener,
        ListElementDataSetter<GeneratedPassword> {
        init {
            v.setOnClickListener(this)
        }

        var isHidden = true

        override fun onClick(p0: View?) {
            //nothing
        }

        private fun getHiddenPass(normalPass: String) = normalPass.substring(startIndex = 0, endIndex = normalPass.length / 2) + "*".repeat(normalPass.length / 2)

        override fun bindData(el: GeneratedPassword) {
            v.password_el_pass.text = getHiddenPass(el.password)
            v.tap_to_revlea_btn.setOnClickListener {
                isHidden = !isHidden
                v.password_el_pass.text = if(isHidden) getHiddenPass(el.password) else el.password
            }
            v.password_gen_time.text = el.time

        }

    }

    fun updateList(newList : ArrayList<GeneratedPassword>) {
        listData = newList
        notifyDataSetChanged()
    }

}