package com.example.currencyapp.ui.convert_currency.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.currencyapp.R
import com.example.currencyapp.data.pojo.SimpleModel
import com.example.currencyapp.databinding.ItemSpinnerColorBinding
import javax.inject.Inject

class ItemsAdapter @Inject constructor() : BaseAdapter() {

    private var list = ArrayList<SimpleModel>()

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): SimpleModel = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val mBinding = DataBindingUtil.inflate<ItemSpinnerColorBinding>(
            LayoutInflater.from(parent!!.context!!),
            R.layout.item_spinner_color, parent, false
        )

        val mViewHolder = ViewHolder(mBinding)

        mViewHolder.bind(list[position])

        return mBinding.root
    }


    inner class ViewHolder(private val binding: ItemSpinnerColorBinding) {

        fun bind(data: SimpleModel) {

            binding.tv.text = data.name

            binding.executePendingBindings()
        }

    }

    fun submitData(d: SimpleModel) {
        this.list.add(d)
        notifyDataSetChanged()
    }

    fun submitData(d: List<SimpleModel>) {
        this.list.addAll(d)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.list.clear()
        notifyDataSetChanged()
    }

}