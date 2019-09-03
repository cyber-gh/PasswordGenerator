package com.skyIT.passwordgenerator.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.gui.HistoryFragment
import java.lang.RuntimeException

class MainAdapter(fm : FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
    private val fragments = listOf(
        HistoryFragment(),
        HistoryFragment()
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[0]
            1 -> fragments[1]
            else -> throw RuntimeException("Undefiend Fragment") as Throwable
        }
    }

    override fun getCount() = 2

    override fun getItemPosition(`object`: Any): Int {
        return 0
    }

    override fun getPageTitle(position: Int) = when (position) {
        0 -> context.resources.getString(R.string.generator)
        1 -> context.resources.getString(R.string.history)
        else -> "Undefined fragment"
    }
}