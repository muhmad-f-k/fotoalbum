package com.example.fotoalbum.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fotoalbum.R
import com.example.fotoalbum.databinding.TitleFragmentBinding
import com.example.fotoalbum.api.JsonApi
import com.example.fotoalbum.repository.Repository
import retrofit2.HttpException
import java.io.IOException

const val TAG = "TitleFragment"


class TitleFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: TitleFragmentBinding
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.title_fragment,
            container,
            false
        )

        val repository = Repository()
        val viewModelFactory = OverviewViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)
        viewModel.getUsers()

        viewModel.myResponse.observe(this, Observer { response ->
            Log.d("Response", response[r].toString())
        })

        binding.overviewViewModel = viewModel
        binding.setLifecycleOwner(this)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                JsonApi.retrofitService.getUsers()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                usersAdapter.Users = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }

        return inflater.inflate(R.layout.title_fragment, container, false)
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        usersAdapter = UsersAdapter()
        adapter = usersAdapter
        layoutManager
    }
}