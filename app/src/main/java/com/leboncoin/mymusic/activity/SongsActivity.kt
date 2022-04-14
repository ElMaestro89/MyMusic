package com.leboncoin.mymusic.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.leboncoin.mymusic.R
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

        // Init Toolbar
        setSupportActionBar(binding.toolbar)

        initList()
        initData()
        loadData()
    }

    private fun initList() {
        binding.rvAlbums.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            setHasFixedSize(true)

            adapter = SongsAdapter(songsViewModel.songs.value ?: mutableListOf()) {
                // Not used yet
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData() {
        songsViewModel.songs.observe(this) {
            binding.rvAlbums.adapter?.notifyDataSetChanged()
        }
    }

    private fun loadData() {
        songsViewModel.getSongs()
    }

    /**************
     *    MENU    *
     **************/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //TODO: Check if online or not
        when (item.itemId) {
            R.id.action_db -> songsViewModel.getDBSongs()
            R.id.action_db_delete -> songsViewModel.deleteAllSongs()
            R.id.action_refresh -> songsViewModel.getSongs()
        }

        return super.onOptionsItemSelected(item)
    }
}
