package com.example.currencyapp.ui.convert_currency

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currencyapp.data.pojo.ConvertCurrencyResponse
import com.example.currencyapp.data.pojo.SimpleModel
import com.example.currencyapp.databinding.FragmentConvertCurrencyBinding
import com.example.currencyapp.ui.MainActivity
import com.example.currencyapp.ui.base.BaseFragment
import com.example.currencyapp.ui.convert_currency.adapters.ItemsAdapter
import com.example.currencyapp.utils.NetworkState
import com.example.currencyapp.utils.Utils.getCurrencies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConvertCurrencyFragment : BaseFragment() {

    private lateinit var binding: FragmentConvertCurrencyBinding
    private val viewModel: ConvertCurrencyViewModel by viewModels()

    @Inject
    lateinit var currenciesAdapter: ItemsAdapter

    private val TAG = ConvertCurrencyFragment::class.java.simpleName

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

        setupUi()
        data()
        observe()

    }

    private var fromTxt = ""
    private var toTxt  = ""

    private fun setupUi(){

        val allCurrencies =
            getCurrencies().map {
                SimpleModel( it)
            }
        currenciesAdapter.submitData(allCurrencies)

        binding.currenciesSpinnerFrom.adapter = currenciesAdapter
        binding.currenciesSpinnerTo.adapter = currenciesAdapter

        if (binding.currenciesSpinnerFrom.selectedItemPosition >= 0) {
            Log.e(TAG, "setupUi: ${currenciesAdapter.getItem(binding.currenciesSpinnerFrom.selectedItemPosition).name} " )
            fromTxt = currenciesAdapter.getItem(binding.currenciesSpinnerFrom.selectedItemPosition).name
        }

        binding.currenciesSpinnerTo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.e(TAG, "setupUi: ${currenciesAdapter.getItem(position).name} " )
                    toTxt = currenciesAdapter.getItem(position).name
                }
                override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            }

        binding.currenciesSpinnerFrom.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.e(TAG, "setupUi: ${currenciesAdapter.getItem(position).name} " )
                    fromTxt = currenciesAdapter.getItem(position).name
                }
                override fun onNothingSelected(p0: AdapterView<*>?) = Unit
            }

        binding.proceed.setOnClickListener {


            if (toTxt.isNotEmpty() && fromTxt.isNotEmpty() && binding.etAmount.text.toString().isNotEmpty())
            {
                viewModel.convertCurrencies(
                    fromTxt,toTxt,binding.etAmount.text.toString()
                )
            }
            else{
                if (binding.etAmount.text.toString().isEmpty())
                    binding.etAmount.error = "Amount is required"
            }

        }


    }

    private fun data() {
//        viewModel.getCurrencies()
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.convertCurrencyStateFlow.collect {
                when (it) {
                    is NetworkState.Idle -> {
                        return@collect
                    }
                    is NetworkState.Loading -> {
                        visProgress(true)
                    }
                    is NetworkState.Error -> {
                        visProgress(false)
                        it.handleErrors(mContext, null)
                    }
                    is NetworkState.Result<*> -> {
                        visProgress(false)
                        handleResult(it.response)
                    }
                }
            }
        }

    }

    private fun <T> handleResult(response: T) {

        Log.e(TAG, "handleResult: id ${response}")

        when (response) {

            is ConvertCurrencyResponse -> when (response.success) {
                true -> ui(response)
                else -> NetworkState.Error(400, "You have entered an invalid value")
                    .handleErrors(mContext)
            }
        }

    }


    private fun <T> ui(data: T) {

        when(data){

            is ConvertCurrencyResponse->{
                binding.convertAmount.text = data.result.toString()
            }

        }

    }

    private fun visProgress(b: Boolean) {

        (mActivity as MainActivity).visibleProgress(b)
    }

}