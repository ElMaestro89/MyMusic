package com.leboncoin.mymusic.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.mymusic.activity.adapter.holder.SongHolder
import com.leboncoin.mymusic.databinding.HolderSongBinding
import com.leboncoin.mymusic.poko.Song

class SongsAdapter(private val songs: MutableList<Song>, private val onClick: (Song) -> Unit) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val binding = HolderSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SongHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}
