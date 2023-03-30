package com.enesigneci.satellite.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.enesigneci.satellite.base.BindingFragment
import com.enesigneci.satellite.databinding.FragmentDetailBinding
import com.enesigneci.satellite.extension.showErrorDialog
import com.enesigneci.satellite.list.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BindingFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSatelliteDetail()
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    hideLoader()
                    requireContext().showErrorDialog(it.exception.message.toString())
                }
                is Resource.Success -> {
                    hideLoader()
                    with(it.data) {
                        binding.apply {
                            tvName.text = title
                            tvCost.text = cost
                            tvDate.text = date
                            tvHeightMass.text = heightMass
                        }
                    }

                }
                Resource.Loading -> showLoader()
            }
        }

        viewModel.positionsLiveData.observe(viewLifecycleOwner) { position ->
            binding.tvLastPosition.text = position
        }
    }

    private fun showLoader() {
        binding.pbLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.pbLoader.visibility = View.GONE
    }
}
