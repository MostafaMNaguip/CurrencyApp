package com.example.currencyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import com.example.currencyapp.R
import com.example.currencyapp.databinding.ActivityMainBinding
import com.example.currencyapp.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: BaseViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val tAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    fun visibleProgress(state: Boolean) {

        if (state) {
            binding.progressLayout.visibility = View.VISIBLE
        } else {
            binding.progressLayout.visibility = View.GONE
        }
    }

}