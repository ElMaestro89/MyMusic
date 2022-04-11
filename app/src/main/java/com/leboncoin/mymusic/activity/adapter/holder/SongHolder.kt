package com.leboncoin.mymusic.activity.adapter.holder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leboncoin.mymusic.databinding.HolderSongBinding
import com.leboncoin.mymusic.poko.Song

class SongHolder (binding: HolderSongBinding, private val onClick: (Song) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    private var songHolder: Song? = null

    private val ivThumbnail: ImageView = binding.ivThumbnail
    private val tvSongNumber: TextView = binding.tvSongNumber
    private val tvTitle: TextView = binding.tvTitle

    init {
        binding.root.setOnClickListener {
            songHolder?.let(onClick)
        }
    }

    fun bind(song: Song) {
        songHolder = song

        with(song) {
            ivThumbnail.load(thumbnailUrl)

            tvSongNumber.text = id.toString()
            tvTitle.text = title
        }
    }
}
