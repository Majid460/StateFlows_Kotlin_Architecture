package com.example.testapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
    }

    abstract fun getLayoutResourceId(): Int
    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}