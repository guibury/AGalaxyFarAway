package com.guilhermebury.agalaxyfaraway.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

import java.util.ArrayList

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class BottomViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val fragments = ArrayList<Fragment>()
    var currentFragment: Fragment? = null
        private set

    fun add(fragment: Fragment) {
        this.fragments.add(fragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (currentFragment !== `object`) {
            currentFragment = `object` as Fragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}
