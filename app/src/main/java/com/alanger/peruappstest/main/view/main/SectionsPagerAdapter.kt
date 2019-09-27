package com.alanger.peruappstest.main.view.main

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager, tabHeader: ArrayList<String>) : FragmentPagerAdapter(fm) {

    private val instantiatedFragments = SparseArray<WeakReference<Fragment>>()


    private val TAB_TITLES: List<String>


    init {
        this.TAB_TITLES = tabHeader
    }

    override fun getItem(position: Int): Fragment? {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        var fragment: Fragment?

        if (position == 0) {
            fragment = PostEndpointListFragment.newInstance("", "")
        } else {
            fragment = PostSavedListFragment.newInstance("", "")
        }

        return fragment

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        instantiatedFragments.put(position, WeakReference(fragment))
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        instantiatedFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }


    fun getFragment(position: Int): Fragment? {
        val wr = instantiatedFragments.get(position)
        return wr?.get()
    }


}