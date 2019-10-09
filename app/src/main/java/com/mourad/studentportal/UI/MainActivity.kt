package com.mourad.studentportal.UI

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mourad.studentportal.Constants.Companion.ADD_PORTAL_REQUEST_CODE
import com.mourad.studentportal.Constants.Companion.EXTRA_PORTAL
import com.mourad.studentportal.Portal
import com.mourad.studentportal.PortalAdapter
import com.mourad.studentportal.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_portal.*

class MainActivity : AppCompatActivity() {
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { startAddActivity() }
        clPortal.setOnClickListener { Snackbar.make(clPortal, "fkjdhgfuoshg", Snackbar.LENGTH_LONG).show() }
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    private fun onClickPortal() {
        var url = "https://paul.kinlan.me/"
        var builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        var customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val reminder = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(reminder)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
