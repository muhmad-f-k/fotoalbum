package com.example.fotoalbum

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.fotoalbum.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    private val viewModel: FotoalbumViewModel by activityViewModels()

    private lateinit var binding: TitleFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        binding.fotoalbumViewModel = viewModel
        binding.setLifecycleOwner(this)

        return inflater.inflate(R.layout.title_fragment, container, false)
    }
}