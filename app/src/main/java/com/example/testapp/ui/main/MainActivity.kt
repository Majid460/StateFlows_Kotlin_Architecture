package com.example.testapp.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.base.BaseActivity
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.ui.main.adapter.BooksAdapter
import com.example.testapp.utils.Status
import com.example.testapp.utils.hide
import com.example.testapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: BaseActivity() {
    private lateinit var  mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:BooksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel= ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mainViewModel.getAllAuthors()

        binding.apply {
            btnDelete.setOnClickListener {
                mainViewModel.deleteBooks()
            }
            btnFetch.setOnClickListener {
                mainViewModel.getBooks()
            }
        }
        setUpAdapter()
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.booksStateFlow.collect {
                    when (it.status) {
                        Status.LOADING ->{
                            binding.PBar.show()
                        }
                        Status.SUCCESS -> {
                             binding.PBar.hide()
                             if(it.data?.isEmpty() == true){
                                 binding.tvNoData.show()
                             }else {binding.tvNoData.hide()}
                             adapter.setItems(it.data?: arrayListOf())
                        }
                        Status.ERROR -> {
                            binding.PBar.hide()
                            println("Shared Flow Error:: "+it.message)}
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
  private fun setUpAdapter(){
      adapter = BooksAdapter(this){}
      binding.apply {
          rvBooks.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
          rvBooks.adapter = adapter
      }
  }
}