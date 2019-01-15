package com.maspamz.footballclub.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import com.maspamz.footballclub.fragment.ClubFavoriteFragment
import com.maspamz.footballclub.fragment.MatchFavoriteFragment
import com.maspamz.footballclub.fragment.MatchLastFragment
import com.maspamz.footballclub.fragment.MatchNextFragment

/**
 * Created by Maspamz on 17/09/2018.
 *
 */

class FavoritePager(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MatchFavoriteFragment()
            1 -> ClubFavoriteFragment()
            else -> return null

        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}