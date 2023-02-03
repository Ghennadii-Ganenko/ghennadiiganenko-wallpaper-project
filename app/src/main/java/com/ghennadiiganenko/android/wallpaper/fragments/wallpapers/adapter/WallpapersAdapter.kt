package com.ghennadiiganenko.android.wallpaper.fragments.wallpapers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ghennadiiganenko.android.wallpaper.R
import com.ghennadiiganenko.android.wallpaper.databinding.ItemWallpaperBinding
import com.ghennadiiganenko.android.wallpaper.domain.module.Hit
import com.squareup.picasso.Picasso

class WallpapersAdapter(
    private val clickListener: ItemClickListener,
    private val view: View,
) : ListAdapter<Hit, RecyclerView.ViewHolder>(ItemsDiffCallback()) {

    private class ItemsDiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(
            oldItem: Hit,
            newItem: Hit
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Hit,
            newItem: Hit
        ): Boolean =
            oldItem.id == newItem.id
    }

    inner class WallpapersViewHolder(private val binding: ItemWallpaperBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hit) {
            binding.apply {

                root.setOnClickListener {
                    it.isClickable = false
                    it.postDelayed({ it.isClickable = true }, 1000)
                    clickListener.onItemClicked(item, view)
                }

               Picasso.get()
                     .load(root.context.resources.getString(R.string.image_url, item.largeImageURL))
                     .into(wallpaperImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersAdapter.WallpapersViewHolder =
        WallpapersViewHolder(
            ItemWallpaperBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val packHolder = holder as WallpapersAdapter.WallpapersViewHolder
        packHolder.bind(item)
    }

    interface ItemClickListener {
        fun onItemClicked(item: Hit, view: View)
    }
}