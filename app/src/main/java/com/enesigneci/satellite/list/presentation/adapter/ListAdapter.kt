package com.enesigneci.satellite.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.satellite.R
import com.enesigneci.satellite.databinding.ItemSatelliteBinding
import com.enesigneci.satellite.list.data.db.model.SatelliteList

class ListAdapter : RecyclerView.Adapter<ListAdapter.SatelliteViewHolder>() {
    private var items: List<SatelliteList> = listOf()
    private var onItemClickedListener: (item: SatelliteList) -> Unit = { satellite -> }

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

    fun updateList(newTodos: List<SatelliteList>) {
        val diffCallBack = SatelliteDiffUtil(items, newTodos)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
        items = newTodos
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

    override fun getItemCount(): Int = items.size
}
