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
        viewModel.uiLiveData.observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Error -> {
                    hideLoader()
                    requireContext().showErrorDialog(it.exception.message.toString())
                }
                is Resource.Success -> {
                    hideLoader()
                    /*Log.d("satellite detail", it.data.toString())
                    Toast.makeText(requireContext(), it.data.id.toString(), Toast.LENGTH_SHORT).show()
                    binding.apply {
                        *//*with(it.data) {
                            tvName.text = name
                            tvCost.text = getString(R.string.cost, DecimalFormat("#,###,###", DecimalFormatSymbols().apply { groupingSeparator = '.' }).format(costPerLaunch))
                            val inputDate = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(firstFlight)
                            val outputDate = SimpleDateFormat("dd.mm.yyyy", Locale.getDefault()).format(inputDate)
                            tvDate.text = outputDate.toString()
                            tvHeightMass.text = getString(R.string.height_mass, "$height / $mass")
                        }*//*
                    }*/
                    viewModel.uiModelLiveData.observe(viewLifecycleOwner, { uiModel ->
                        with(uiModel) {
                            binding.apply {
                                tvName.text = title
                                tvCost.text = cost
                                tvDate.text = date
                                tvHeightMass.text = heightMass
                            }
                        }

                    })
                }
                Resource.Loading -> showLoader()
            }
        })
    }

    private fun showLoader() {
        binding.pbLoader.visibility = View.VISIBLE
    }
    private fun hideLoader() {
        binding.pbLoader.visibility = View.GONE
    }
}
