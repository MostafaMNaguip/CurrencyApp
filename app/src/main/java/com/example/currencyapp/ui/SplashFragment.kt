package com.example.currencyapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.currencyapp.R
import com.example.currencyapp.databinding.FragmentSplashBinding
import com.example.currencyapp.ui.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding
//    private lateinit var mContext: Context
//    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(
            layoutInflater,container,false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mContext = binding.root.context
//        navController = Navigation.findNavController(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)

                navController.navigate(R.id.convertCurrencyFragment)
        }

    }


}