package com.maspamz.footballclub

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.maspamz.footballclub.adapter.pager.FavoritePager
import kotlinx.android.synthetic.main.activity_main_favorite.*

/**
 * Created by Maspamz on 19/09/2018.
 *
 */

class MainFavorite : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_favorite)
        supportActionBar?.title = "Football Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.colorBlueJeans)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlueJeansDark)
        }

        configureTabLayout()
    }

    private fun configureTabLayout() {

        l_tab_favorite.addTab(l_tab_favorite.newTab().setText("Match"))
        l_tab_favorite.addTab(l_tab_favorite.newTab().setText("Club"))

        val adapter = FavoritePager(supportFragmentManager,
                l_tab_favorite.tabCount)
        l_viewpager_favorite.adapter = adapter

        l_viewpager_favorite.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(l_tab_favorite))
        l_tab_favorite.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                l_viewpager_favorite.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }
}