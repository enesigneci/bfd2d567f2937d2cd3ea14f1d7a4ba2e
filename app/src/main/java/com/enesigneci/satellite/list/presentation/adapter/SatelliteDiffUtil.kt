package com.enesigneci.satellite.list.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesigneci.satellite.list.data.db.model.SatelliteList

class SatelliteDiffUtil(
    private val oldList: List<SatelliteList>,
    private val newList: List<SatelliteList>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}
