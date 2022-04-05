package com.example.fotoalbum.album_photos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fotoalbum.MyClickListener
import com.example.fotoalbum.R
import com.example.fotoalbum.databinding.AlbumPhotosFragmentBinding
import com.example.fotoalbum.repository.Repository
import com.example.fotoalbum.user_albums.UserAlbumsDirections

class AlbumPhotos : Fragment(), MyClickListener {

    private lateinit var viewModel: AlbumPhotosViewModel
    private lateinit var binding: AlbumPhotosFragmentBinding
    private val photosAdapter by lazy { PhotosAdapter(this) }

    val args: AlbumPhotosArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.album_photos_fragment,
            container,
            false
        )

        setupRecyclerView()
        val repository = Repository()
        binding.lifecycleOwner = this
        val viewModelFactory = AlbumPhotosViewModelFactory(repository)
        val albumId = args.albumId
        viewModel = ViewModelProvider(this, viewModelFactory)[AlbumPhotosViewModel::class.java]
        viewModel.getPhotos(albumId)
        binding.viewModel = viewModel

        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { photosAdapter.setData(it) }
            }
        })

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = photosAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClick(position: Int) {
        var photoId = photosAdapter.photoList[position].id
        var action = AlbumPhotosDirections.actionAlbumPhotosToFullPhoto(photoId)
        view?.findNavController()?.navigate(action)
    }

}