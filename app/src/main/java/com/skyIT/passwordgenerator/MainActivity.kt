package com.skyIT.passwordgenerator

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.room.Room
import androidx.viewpager.widget.PagerAdapter
import com.skyIT.passwordgenerator.adapters.MainAdapter
import com.skyIT.passwordgenerator.data.AppDatabase
import com.skyIT.passwordgenerator.gui.HistoryFragment
import com.skyIT.passwordgenerator.gui.HistoryFragmentV2
import com.skyIT.passwordgenerator.gui.Router
import com.skyIT.passwordgenerator.gui.TestFragment
import com.thinkit.skylib.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {

    override fun initialize() {
        super.initialize()
        Router.acitivity = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HistoryFragment(), "Test fragment")
        adapter.addFragment(HistoryFragment(), "Test fragment 2")

        mainViewPager.adapter = adapter
//        tabLayout.setupWithViewPager(mainViewPager)
        //initalizeTabLayout()
    }


    private fun initalizeTabLayout() {
//        mainViewPager.adapter = PagerAdapterFactory.getPagerAdapter(listOf(
//            HistoryFragment(), HistoryFragment()
//        ), supportFragmentManager)
        //tabLayout.setupWithViewPager(mainViewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}
