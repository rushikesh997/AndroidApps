package com.rushikeshb.currencyex.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rushikeshb.currencyex.R
import com.rushikeshb.currencyex.databinding.ListRateItemsBinding
import com.rushikeshb.currencyex.utils.RateUtils.getCodeName
import com.rushikeshb.currencyex.utils.RateUtils.getFlag

class RatesAdapter : ListAdapter<Pair<String, Double>, RatesAdapter.RatesViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val binding = ListRateItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val currentRate = getItem(position)
        holder.bind(currentRate)
    }

    class RatesViewHolder(val binding: ListRateItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentRate: Pair<String, Double>) {
            val currencyName = currentRate.first
            val currencyRate = currentRate.second

            if(getCodeName(currencyName) !=  binding.root.resources.getString(R.string.none)) {
                binding.imgCountryFlag.setImageResource(getFlag(currencyName))
                binding.txtCurrencyCode.text = currencyName
                binding.txtCurrencyName.text = getCodeName(currencyName)
                if (currentRate.second == 1.0) {
                    binding.txtCurrencyRate.text = "1"
                } else {
                    binding.txtCurrencyRate.text = String.format("%.4f", currencyRate)
                }
            }else{
                binding.root.visibility = View.GONE
                binding.root.layoutParams = RecyclerView.LayoutParams(0, 0)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Pair<String, Double>>() {
    override fun areItemsTheSame(oldItem: Pair<String, Double>, newItem: Pair<String, Double>): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(oldItem: Pair<String, Double>, newItem: Pair<String, Double>): Boolean {
        return oldItem == newItem
    }
}
