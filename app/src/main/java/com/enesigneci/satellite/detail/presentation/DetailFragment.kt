package com.enesigneci.satellite.detail.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.satellite.base.BindingFragment
import com.enesigneci.satellite.databinding.FragmentDetailBinding
import com.enesigneci.satellite.databinding.FragmentListBinding
import com.enesigneci.satellite.extension.showErrorDialog
import com.enesigneci.satellite.list.data.Resource
import com.enesigneci.satellite.list.presentation.adapter.ListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BindingFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiLiveData.observe(viewLifecycleOwner, {

        })
    }
}
