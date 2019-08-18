package com.skyIT.passwordgenerator.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.skyIT.passwordgenerator.R
import com.skyIT.passwordgenerator.gui.GeneratorFragment
import com.skyIT.passwordgenerator.gui.HistoryFragment

class MainAdapter(fm : FragmentManager, private val context: Context) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GeneratorFragment()
            1 -> HistoryFragment()
            else -> Fragment()
        }
    }

    override fun getCount() = 2


    override fun getPageTitle(position: Int) = when (position) {
        0 -> context.resources.getString(R.string.generator)
        1 -> context.resources.getString(R.string.history)
        else -> "Undefined fragment"
    }
}