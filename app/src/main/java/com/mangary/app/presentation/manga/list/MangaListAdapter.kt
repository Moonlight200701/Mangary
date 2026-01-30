package com.mangary.app.presentation.manga.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mangary.app.R
import com.mangary.app.domain.model.Manga

/**
 * RecyclerView adapter for manga list
 */
class MangaListAdapter(
    private val onItemClick: (Manga) -> Unit
) : ListAdapter<Manga, MangaListAdapter.MangaViewHolder>(MangaDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_manga, parent, false)
        return MangaViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = getItem(position)
        holder.bind(manga)
    }
    
    inner class MangaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coverImage: ImageView = itemView.findViewById(R.id.ivMangaCover)
        private val titleText: TextView = itemView.findViewById(R.id.tvMangaTitle)
        private val statusText: TextView = itemView.findViewById(R.id.tvMangaStatus)
        private val descriptionText: TextView = itemView.findViewById(R.id.tvMangaDescription)
        
        fun bind(manga: Manga) {
            titleText.text = manga.title
            statusText.text = "Status: ${manga.status}"
            descriptionText.text = if (manga.description.length > 150) {
                manga.description.take(150) + "..."
            } else {
                manga.description
            }
            
            // Load cover image using Coil
            coverImage.load(manga.coverImageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }
            
            itemView.setOnClickListener {
                onItemClick(manga)
            }
        }
    }
    
    class MangaDiffCallback : DiffUtil.ItemCallback<Manga>() {
        override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem == newItem
        }
    }
}
