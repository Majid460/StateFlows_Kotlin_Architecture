package com.example.testapp.ui.main

import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testapp.R
import com.example.testapp.base.BaseActivity
import com.example.testapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var  mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel= ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getAllAuthors()
        val btnFetch=findViewById<AppCompatButton>(R.id.btn)
        val btnDelete = findViewById<AppCompatButton>(R.id.btn1)
        btnDelete.setOnClickListener {
            mainViewModel.deleteBooks()
        }
        btnFetch.setOnClickListener {
            mainViewModel.getBooks()
        }

        lifecycleScope.launch(Dispatchers.Default) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.booksStateFlow.collect {
                    when (it.status) {
                        Status.SUCCESS -> {println("Shared Flow Books:: "+it.data)}
                        Status.ERROR -> {println("Shared Flow Error:: "+it.message)}
                        else -> {}
                    }
                }
            }
        }
        lifecycleScope.launch(Dispatchers.Default) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.authorsStateFlow.collect {
                    when (it.status) {
                        Status.SUCCESS -> {println("Shared Flow authors:: "+it.data)}
                        Status.ERROR -> {println("Shared Flow Error:: "+it.message)}
                        else -> {}
                    }
                }
            }
        }
    }
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }
}