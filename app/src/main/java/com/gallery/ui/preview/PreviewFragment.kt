package com.gallery.ui.preview

import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gallery.R
import com.gallery.databinding.FragmentPreviewBinding

class DetailFragment : Fragment() {

    private var binding: FragmentPreviewBinding? = null
    private val viewModel: PreviewViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPreviewBinding.inflate(inflater).apply {
            this@DetailFragment.binding = this
            viewModel = this@DetailFragment.viewModel
            lifecycleOwner = this@DetailFragment.viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.toolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
        binding?.toolbar?.menu?.findItem(R.id.action_download)?.setOnMenuItemClickListener {
            downloadImage()
            true
        }
        viewModel.setPhoto(args.photo)
    }

    private fun downloadImage(){
        viewModel.photo.value?.imageUrl?.let {
            val name = it.substringAfterLast("/")
            val request = DownloadManager.Request(Uri.parse(it)).apply {
                setDescription(getString(R.string.downloading))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, name)
            }
            val manager = requireActivity().getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
//            Toast.ma
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}