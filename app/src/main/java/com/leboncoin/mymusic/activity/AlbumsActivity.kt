package com.leboncoin.mymusic.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.leboncoin.mymusic.R
import com.leboncoin.mymusic.activity.adapter.AlbumExpandableAdapter
import com.leboncoin.mymusic.databinding.ActivityAlbumsBinding
import com.leboncoin.mymusic.viewmodel.AlbumsViewModel

class AlbumsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumsBinding

    private val albumsViewModel: AlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initExpandableList()
        initData()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initExpandableList() {
        binding.elvAlbums.setAdapter(AlbumExpandableAdapter(albumsViewModel.albums.value ?: mutableListOf()))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initData() {
        albumsViewModel.albums.observe(this) {
            (binding.elvAlbums.expandableListAdapter as AlbumExpandableAdapter).notifyDataSetChanged()
        }

        albumsViewModel.getAlbums()
    }

    /**************
     *    MENU    *
     **************/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_db -> albumsViewModel.getDBAlbums()
            R.id.action_db_delete -> albumsViewModel.deleteAllAlbums()
            R.id.action_refresh -> albumsViewModel.getAlbums()
        }

        return super.onOptionsItemSelected(item)
    }
}
