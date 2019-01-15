package com.maspamz.footballclub

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.maspamz.footballclub.adapter.pager.MatchPager
import kotlinx.android.synthetic.main.activity_main_match.*

/**
 * Created by Maspamz on 19/09/2018.
 *
 */

class MainMatch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_match)
        supportActionBar?.title = "Football Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F

        configureTabLayout()
    }

    private fun configureTabLayout() {

        l_tab_match.addTab(l_tab_match.newTab().setText("Next Match"))
        l_tab_match.addTab(l_tab_match.newTab().setText("Last Match"))

        val adapter = MatchPager(supportFragmentManager,
                l_tab_match.tabCount)
        l_viewpager.adapter = adapter

        l_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(l_tab_match))
        l_tab_match.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                l_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

}