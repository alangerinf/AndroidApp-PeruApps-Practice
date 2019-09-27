package com.alanger.peruappstest.main.view

import android.content.Context
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.alanger.peruappstest.R
import com.alanger.peruappstest.main.view.main.PostEndpointListFragment
import com.alanger.peruappstest.main.view.main.PostSavedListFragment
import com.alanger.peruappstest.main.view.main.SectionsPagerAdapter

import java.util.ArrayList

class TabbetActivity : AppCompatActivity(), PostEndpointListFragment.OnFragmentInteractionListener, PostSavedListFragment.OnFragmentInteractionListener {
    internal var tittles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbet)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        declare()

    }


    private fun declare() {

        tittles = ArrayList()
        tittles.add(getString(R.string.tab_text_1))
        tittles.add(getString(R.string.tab_text_2))


        sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, tittles)


        viewPager = findViewById(R.id.view_pager)
        viewPager!!.adapter = sectionsPagerAdapter

        tabs = findViewById(R.id.tabs)
        tabs!!.setupWithViewPager(viewPager)

        fab = findViewById(R.id.fab)
        fab!!.setOnClickListener(View.OnClickListener {

            viewPager!!.setCurrentItem(1,true)
            declare()
        })

    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onFragmentInteraction_upd_eTextDNI(mensaje: String) {

    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    companion object {

        private var fab: FloatingActionButton? = null
        private var viewPager: CustomViewPager? = null
        private var tabs: TabLayout? = null
        private var sectionsPagerAdapter: SectionsPagerAdapter? = null
    }
}