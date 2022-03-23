package com.example.fotoalbum.album_photos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fotoalbum.R

class AlbumPhotos : Fragment() {

    companion object {
        fun newInstance() = AlbumPhotos()
    }

    private lateinit var viewModel: AlbumPhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_photos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumPhotosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}