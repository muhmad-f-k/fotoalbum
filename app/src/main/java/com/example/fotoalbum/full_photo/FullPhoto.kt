package com.example.fotoalbum.full_photo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fotoalbum.R

class FullPhoto : Fragment() {

    companion object {
        fun newInstance() = FullPhoto()
    }

    private lateinit var viewModel: FullPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_photo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FullPhotoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}