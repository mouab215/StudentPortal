package com.mourad.studentportal.UI

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.mourad.studentportal.Constants.Companion.EXTRA_PORTAL
import com.mourad.studentportal.Portal
import com.mourad.studentportal.R
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create a Portal"
        btnAddPortal.setOnClickListener { onSaveClick() }
    }

    private fun onSaveClick() {
        if (etTitle.text.isNullOrEmpty()) {
            Snackbar.make(etTitle, R.string.empty_field_error_title, Snackbar.LENGTH_LONG).show()
        } else if (etUrl.text.isNullOrEmpty()) {
            Snackbar.make(etUrl, R.string.empty_field_error_url, Snackbar.LENGTH_LONG).show()
        } else {
            var portal = Portal(etTitle.text.toString(), etUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
