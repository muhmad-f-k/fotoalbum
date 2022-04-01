package com.example.fotoalbum.user_albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fotoalbum.MyClickListener
import com.example.fotoalbum.R
import com.example.fotoalbum.model.Album
import kotlinx.android.synthetic.main.list_item.view.*

class AlbumsAdapter(val listener: MyClickListener) : RecyclerView.Adapter<AlbumsAdapter.UsersViewHolder>() {

    var albumList = emptyList<Album>()

    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)

            }

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }


    override fun getItemCount() = albumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.itemView.txtUser.text = albumList[position].title
    }

    fun setData(newList: List<Album>){
        albumList = newList
        notifyDataSetChanged()
    }

}