package com.example.fotoalbum.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fotoalbum.MyClickListener
import com.example.fotoalbum.R
import com.example.fotoalbum.databinding.TitleFragmentBinding
import com.example.fotoalbum.repository.Repository

class TitleFragment : Fragment(), MyClickListener {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: TitleFragmentBinding
    private val usersAdapter by lazy { UsersAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        setupRecyclerView()
        val repository = Repository()
        binding.lifecycleOwner = this
        val viewModelFactory = OverviewViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[OverviewViewModel::class.java]
        viewModel.getUsers()
        binding.overviewViewModel = viewModel

        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { usersAdapter.setData(it) }
            }
        })

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = usersAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClick(position: Int) {
        var userId = usersAdapter.userList[position].id
        var action = TitleFragmentDirections.actionTitleFragmentToUserAlbums(userId)
        view?.findNavController()?.navigate(action)
        Log.d("Response", "${usersAdapter.userList[position].id}")
        Log.d("Response", "${usersAdapter.userList[position].name}")

    }

}