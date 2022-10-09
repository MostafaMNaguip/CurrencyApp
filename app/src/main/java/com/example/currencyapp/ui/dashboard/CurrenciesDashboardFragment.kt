package com.example.currencyapp.ui.dashboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.currencyapp.R
import com.example.currencyapp.databinding.FragmentCurrenciesDashboardBinding


class CurrenciesDashboardFragment : Fragment() {

    private lateinit var binding: FragmentCurrenciesDashboardBinding
    private lateinit var mContext: Context
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCurrenciesDashboardBinding.inflate(
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