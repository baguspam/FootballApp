package com.maspamz.footballclub.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import com.maspamz.footballclub.fragment.DetailClubDescFragment
import com.maspamz.footballclub.fragment.DetailClubPlayerFragment

/**
 * Created by Maspamz on 17/09/2018.
 *
 */

class DetailClubPager(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> DetailClubDescFragment()
            1 -> DetailClubPlayerFragment()
            else -> return null

        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}