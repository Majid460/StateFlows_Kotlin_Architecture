package com.example.testapp.ui.mvi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testapp.R
import com.example.testapp.base.BaseActivity
import com.example.testapp.databinding.ActivityMviTestBinding
import com.example.testapp.utils.hide
import com.example.testapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MviTestActivity : BaseActivity() {
    private val viewModel: MviTestViewModel by viewModels()
    private lateinit var binding: ActivityMviTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvi_test)
        setClickListeners()
        setUpData()
    }
    private fun setClickListeners(){
        binding.apply {
                btnFetch.setOnClickListener {
                    lifecycleScope.launch {
                    viewModel.userIntent.send(ViewIntent.FetchBooks)
                }
            }
        }
    }
    private fun setUpData() {
        binding.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.state.collect {
                        when (it) {
                            is ViewState.Idle -> {

                            }

                            is ViewState.Loading -> {
                              PBar.show()
                            }

                            is ViewState.Books -> {
                                PBar.hide()
                                println(it.books)
                            }

                            is ViewState.Error -> {
                                PBar.hide()
                                Toast.makeText(this@MviTestActivity, it.error, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
            }

        }
    }
}
