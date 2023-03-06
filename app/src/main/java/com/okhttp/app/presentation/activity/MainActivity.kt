package com.okhttp.app.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.okhttp.app.databinding.ActivityMainBinding
import com.okhttp.app.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }






      viewModel.dataHighPopulation.observe(this){
        binding.textView.text = "Hello from viewModel"
      }
        viewModel.dataLowPopulation.observe(this){
        binding.textView.text = "Hello from viewModel"
      }



    }
}