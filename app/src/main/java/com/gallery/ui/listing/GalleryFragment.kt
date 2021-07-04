package com.gallery.ui.listing

import com.gallery.ui.listing.adapter.ImagesLoadStateAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gallery.R
import com.gallery.databinding.FragmentGalleryBinding
import com.gallery.ui.listing.StateManager.showErrorLoading
import com.gallery.ui.listing.StateManager.showLoading
import com.gallery.ui.listing.StateManager.showNoImagesFound
import com.gallery.ui.listing.StateManager.showSuccessfullyLoaded
import com.gallery.ui.listing.adapter.Adapter
import com.gallery.utils.AfterTextChangeListener
import com.gallery.utils.EventObserver
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var binding: FragmentGalleryBinding? = null
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentGalleryBinding.inflate(layoutInflater, container, false).also {
        binding = it
        it.viewModel = viewModel
        it.lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
        setupObservers()
        setupRecyclerView()
    }

    private fun setupSearch() {
        // setting up clear search button
        binding?.etSearch?.addTextChangedListener(AfterTextChangeListener {
            binding?.ivCross?.visibility = if (it.isNullOrEmpty()) View.GONE else View.VISIBLE
        })
    }

    private fun setupObservers(){
        viewModel.previewImage.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(GalleryFragmentDirections.actionGalleryToPreview(it))
        })
    }

    private fun setupRecyclerView() {

        val adapter = Adapter(viewModel)

        binding?.recyclerView?.layoutManager = StaggeredGridLayoutManager(
            3, StaggeredGridLayoutManager.VERTICAL
        )

        binding?.recyclerView?.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ImagesLoadStateAdapter(adapter),
            footer = ImagesLoadStateAdapter(adapter)
        )

        viewModel.imagesList.observe(viewLifecycleOwner, {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })

        adapter.addLoadStateListener { loadState ->
            updateLoadingState(adapter, loadState.refresh)
        }
    }

    private fun updateLoadingState(adapter: Adapter, refreshState: LoadState) {
        val binding = binding?: return
        when (refreshState) {

            is LoadState.NotLoading -> {
                if (adapter.itemCount < 1) showNoImagesFound(binding)
                else showSuccessfullyLoaded(binding)
            }

            LoadState.Loading -> showLoading(binding)

            is LoadState.Error -> {
                showErrorLoading(binding)

                val errorMsg = refreshState.error.message
                    ?: getString(R.string.generic_error)
                showError(errorMsg)
            }
        }
    }

    private fun showError(errorMsg: String) {
        val binding = binding?: return
        Snackbar.make(binding.root, errorMsg, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { viewModel.retrySearch() }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}