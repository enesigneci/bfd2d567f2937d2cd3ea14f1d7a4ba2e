package com.enesigneci.satellite.list.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enesigneci.satellite.base.BindingFragment
import com.enesigneci.satellite.databinding.FragmentListBinding
import com.enesigneci.satellite.extension.debouncedChangeListener
import com.enesigneci.satellite.extension.showErrorDialog
import com.enesigneci.satellite.list.data.Resource
import com.enesigneci.satellite.list.presentation.adapter.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint

class ListFragment : BindingFragment<FragmentListBinding>(FragmentListBinding::inflate) {
    private val viewModel: ListViewModel by viewModels()
    private val listAdapter by lazy { ListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvList.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            }
            setupSatelliteFilter()
        }

        viewModel.loadSatellites()

        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    hideLoader()
                    requireContext().showErrorDialog(it.exception.message.toString())
                }
                is Resource.Success -> {
                    hideLoader()
                    listAdapter.updateList(it.data)
                    listAdapter.setItemClickListener {
                        val detailAction =
                            ListFragmentDirections.actionListFragmentToDetailFragment(
                                it.id ?: 0,
                                it.name ?: ""
                            )
                        findNavController().navigate(detailAction)
                    }
                }
                Resource.Loading -> showLoader()
            }
        }
    }

    private fun setupSatelliteFilter() {
        lifecycleScope.launch {
            binding.etFilter.debouncedChangeListener().debounce(300).collect {
                listAdapter.filter.filter(it)
            }
        }

    }

    private fun showLoader() {
        binding.pbLoader.visibility = View.VISIBLE
    }
    private fun hideLoader() {
        binding.pbLoader.visibility = View.GONE
    }
}
