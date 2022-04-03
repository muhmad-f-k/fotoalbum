package com.example.fotoalbum.user_albums

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fotoalbum.MyClickListener
import com.example.fotoalbum.R
import com.example.fotoalbum.databinding.TitleFragmentBinding
import com.example.fotoalbum.databinding.UserAlbumsFragmentBinding
import com.example.fotoalbum.overview.OverviewViewModel
import com.example.fotoalbum.overview.OverviewViewModelFactory
import com.example.fotoalbum.overview.TitleFragmentDirections
import com.example.fotoalbum.repository.Repository
import androidx.lifecycle.Observer


class UserAlbums : Fragment(), MyClickListener {

    private lateinit var viewModel: UserAlbumsViewModel
    private lateinit var binding: UserAlbumsFragmentBinding
    private val albumsAdapter by lazy { AlbumsAdapter(this) }

    val args: UserAlbumsArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_albums_fragment,
            container,
            false
        )

        setupRecyclerView()
        val repository = Repository()
        binding.lifecycleOwner = this
        val viewModelFactory = UserAlbumsViewModelFactory(repository)
        val userId = args.userId
        viewModel = ViewModelProvider(this, viewModelFactory)[UserAlbumsViewModel::class.java]
        viewModel.getAlbums(userId)
        binding.viewModel = viewModel

        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { albumsAdapter.setData(it) }
            }
        })

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = albumsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClick(position: Int) {
        var albumId = albumsAdapter.albumList[position].id
        var action = UserAlbumsDirections.actionUserAlbumsToAlbumPhotos(albumId)
        view?.findNavController()?.navigate(action)
        Log.d("Response", "${albumsAdapter.albumList[position].id}")
        Log.d("Response", "${albumsAdapter.albumList[position].title}")

    }

}