package com.manish.androiddevcourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.manish.androiddevcourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(50)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainActivityViewModel::class.java]
        viewModel.count.observe(this) {
            binding.countText.text = it.toString()
        }
        binding.button.setOnClickListener {
            viewModel.updateCount()
        }
    }
}