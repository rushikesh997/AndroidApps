package com.example.covistat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covistat.databinding.CountryRowBinding
import com.squareup.picasso.Picasso

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    private var countriesList: List<CountriesResponse>? = null
    private var countriesListed: List<CountriesResponse>? = null
    private var context:Context? = null

    fun setCountriesList(context: Context?, countriesList: List<CountriesResponse>) {
        this.context = context

        if (this.countriesList == null) {
            this.countriesList = countriesList
            countriesListed = countriesList
            notifyItemChanged(0, countriesListed!!.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun getOldListSize(): Int {
                    return this@CountryAdapter.countriesList!!.size
                }

                override fun getNewListSize(): Int {
                    return countriesList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@CountryAdapter.countriesList!![oldItemPosition].country === countriesList[newItemPosition].country
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newMovie = this@CountryAdapter.countriesList!![oldItemPosition]
                    val oldMovie = countriesList[newItemPosition]
                    return newMovie.country == oldMovie.country
                }
            })
            this.countriesList = countriesList
            countriesListed = countriesList
            result.dispatchUpdatesTo(this)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class CountryHolder(val binding: CountryRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = CountryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.tvCountryDeath.text = "Total Death : " + countriesListed!![position].deaths
        holder.binding.tvCountryName.text = countriesListed!![position].country
        Picasso.get().load(countriesListed!![position].flag).into(holder.binding.ivCountryPoster)
        holder.itemView.setOnClickListener {
            if (onItemClickListener == null) return@setOnClickListener
            onItemClickListener?.onItemClick(countriesListed!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (countriesList != null) {
            countriesListed!!.size
        } else {
            0
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: CountriesResponse)
    }
}