package com.example.fotoalbum.full_photo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.fotoalbum.R
import com.example.fotoalbum.databinding.FullPhotoFragmentBinding
import com.example.fotoalbum.model.Photo
import com.example.fotoalbum.repository.Repository
import kotlinx.android.synthetic.main.full_photo_fragment.view.*

class FullPhoto : Fragment() {

    private lateinit var viewModel: FullPhotoViewModel
    private lateinit var binding: FullPhotoFragmentBinding

    val args: FullPhotoArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.full_photo_fragment,
            container,
            false
        )

        val repository = Repository()
        binding.lifecycleOwner = this
        val viewModelFactory = FullPhotoViewModelFactory(repository)
        val photoId = args.photoId
        viewModel = ViewModelProvider(this, viewModelFactory)[FullPhotoViewModel::class.java]
        viewModel.getFullPhoto(photoId)
        binding.viewModel = viewModel

        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                val imgUrl = response.body()?.url
                viewModel._photoTitle.value = response.body()?.title
                loadPhoto(imgUrl!!)
            }
        })

        viewModel.deleteResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", "Bilde er slettet")
            }
        })

        binding.photoTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
               viewModel.setTitle(char.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.changeTitle.setOnClickListener {
            val photo = Photo(photoId, )
            viewModel.changeTitle(photoId, )
        }

        return binding.root
    }

    fun loadPhoto(imgUrl: String) {
        val url = GlideUrl(
            imgUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(requireContext()))
                .build()
        )
            Glide.with(this)
                .load(url)
                .override(900,900)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.fullPhoto)
    }
}