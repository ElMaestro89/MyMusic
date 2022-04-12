package com.leboncoin.mymusic.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leboncoin.mymusic.activity.adapter.SongsAdapter
import com.leboncoin.mymusic.databinding.ActivitySongsBinding
import com.leboncoin.mymusic.viewmodel.SongsViewModel

class SongsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongsBinding

    private val songsViewModel: SongsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySongsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
        initData()
    }

    private fun initList() {
        binding.rvAlbums.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            setHasFixedSize(true)

            adapter = SongsAdapter(songsViewModel.songs.value ?: mutableListOf()) {
                songsViewModel.getSongs()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData() {
        songsViewModel.songs.observe(this) {
            binding.rvAlbums.adapter?.notifyDataSetChanged()
        }
    }
}
