package com.example.fotoalbum.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fotoalbum.R
import com.example.fotoalbum.RecyclerAdapter
import com.example.fotoalbum.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: TitleFragmentBinding

    private var layoutManager: RecyclerView.LayoutManager? =null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        binding.overviewViewModel = viewModel
        binding.setLifecycleOwner(this)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter=adapter

        return inflater.inflate(R.layout.title_fragment, container, false)
    }
}