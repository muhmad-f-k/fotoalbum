package com.example.fotoalbum.album_photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.fotoalbum.MyClickListener
import com.example.fotoalbum.R
import com.example.fotoalbum.model.Photo
import kotlinx.android.synthetic.main.grid_view_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class PhotosAdapter(val listener: MyClickListener) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    var photoList = emptyList<Photo>()

    inner class PhotosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)

            }

        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }


    override fun getItemCount() = photoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false))
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        var imgUrl = photoList[position].thumbnailUrl
        val url = GlideUrl(
            imgUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(holder.itemView.context))
                .build()
        )

        Glide.with(holder.itemView.context)
            .load(url)
            .override(150, 150)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(holder.itemView.findViewById(R.id.album_photo))
    }

    fun setData(newList: List<Photo>){
        photoList = newList
        notifyDataSetChanged()
    }

}