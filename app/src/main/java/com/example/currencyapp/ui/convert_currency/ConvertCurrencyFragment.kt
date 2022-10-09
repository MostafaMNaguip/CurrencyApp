package com.example.currencyapp.ui.convert_currency

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.currencyapp.R
import com.example.currencyapp.databinding.FragmentConvertCurrencyBinding

class ConvertCurrencyFragment : Fragment() {

    private lateinit var binding: FragmentConvertCurrencyBinding
    private lateinit var mContext: Context
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConvertCurrencyBinding.inflate(
            layoutInflater,container,false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = binding.root.context
        navController = Navigation.findNavController(binding.root)

    }


}