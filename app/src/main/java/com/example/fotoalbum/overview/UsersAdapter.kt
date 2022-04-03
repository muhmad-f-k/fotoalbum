package com.example.fotoalbum.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fotoalbum.MyClickListener
import com.example.fotoalbum.R
import com.example.fotoalbum.model.User
import kotlinx.android.synthetic.main.list_item.view.*


class UsersAdapter(val listener: MyClickListener) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var userList = emptyList<User>()

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)

            }

        }
    }


    private val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }


    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.itemView.txtUser.text = userList[position].name
    }

    fun setData(newList: List<User>){
        userList = newList
        notifyDataSetChanged()
    }

}