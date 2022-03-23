package com.example.fotoalbum.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fotoalbum.network.User

class UserAdapter( val onClickListener: OnClickListener ) :
        ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback){

            class UserViewHolder(private var binding: )
}