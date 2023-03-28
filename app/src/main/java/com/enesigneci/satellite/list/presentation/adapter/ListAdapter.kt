package com.enesigneci.satellite.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.satellite.R
import com.enesigneci.satellite.databinding.ItemSatelliteBinding
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import java.util.Locale

class ListAdapter : RecyclerView.Adapter<ListAdapter.SatelliteViewHolder>(), Filterable {
    private val items = mutableListOf<SatelliteList>()

    private val differCallback = object : DiffUtil.ItemCallback<SatelliteList>() {
        override fun areItemsTheSame(oldItem: SatelliteList, newItem: SatelliteList) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SatelliteList, newItem: SatelliteList) =
            oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickedListener: (item: SatelliteList) -> Unit = { _ -> }

    fun setItemClickListener(onItemClickedListener: (item: SatelliteList) -> Unit) {
        this.onItemClickedListener = onItemClickedListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        val binding =
            ItemSatelliteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatelliteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.bind(currentItem, onItemClickedListener)
    }

    private fun getItem(position: Int): SatelliteList {
        return items[position]
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val charString = charSequence.toString()
            val filteredList = mutableListOf<SatelliteList>()
            if (charString.isEmpty()) {
                filteredList.addAll(items)
            } else {
                filteredList.addAll(items
                    .filter { it.name?.lowercase(Locale.getDefault())?.contains(charString) ?: false })
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
            filterResults?.let { results ->
                val satelliteList = (results.values as List<*>)
                    .filterIsInstance<SatelliteList>()
                    .toList()
                differ.submitList(satelliteList)
            }
        }
    }


    fun updateList(newSatellites: List<SatelliteList>) {
        differ.submitList(newSatellites)
        items.clear()
        items.addAll(newSatellites)
    }

    class SatelliteViewHolder(private val binding: ItemSatelliteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            satellite: SatelliteList,
            onItemClickedListener: (item: SatelliteList) -> Unit
        ) {
            binding.apply {
                val isActive = satellite.isActive ?: false
                tvName.text = satellite.name
                tvName.isEnabled = isActive
                tvStatus.isEnabled = isActive

                if (isActive) {
                    tvStatus.text = tvStatus.context.getString(R.string.active)
                    ivStatusIndicator.setImageResource(R.drawable.status_indicator_active)
                } else {
                    tvStatus.text = tvStatus.context.getString(R.string.passive)
                    ivStatusIndicator.setImageResource(R.drawable.status_indicator_passive)
                }

                root.setOnClickListener {
                    onItemClickedListener(satellite)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
