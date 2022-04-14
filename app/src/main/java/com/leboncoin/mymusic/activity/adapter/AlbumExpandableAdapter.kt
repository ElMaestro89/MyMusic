package com.leboncoin.mymusic.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import coil.load
import com.leboncoin.mymusic.R
import com.leboncoin.mymusic.databinding.HolderAlbumBinding
import com.leboncoin.mymusic.databinding.HolderSongBinding
import com.leboncoin.mymusic.poko.Album
import com.leboncoin.mymusic.poko.Song

class AlbumExpandableAdapter(private val albums: MutableList<Album>) : BaseExpandableListAdapter() {

    private lateinit var bindingGroup: HolderAlbumBinding
    private lateinit var bindingItem: HolderSongBinding

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val album = getGroup(groupPosition) as Album

        bindingGroup = HolderAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view  = bindingGroup.root

        bindingGroup.tvAlbumTitle.text = view.context.getString(R.string.album_title, album.albumId)

        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val song = getChild(groupPosition, childPosition) as Song

        bindingItem = HolderSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        bindingItem.buildItemView(song)

        return bindingItem.root
    }

    private fun HolderSongBinding.buildItemView(song: Song) {
        ivThumbnail.load(song.thumbnailUrl) {
            placeholder(R.drawable.placeholder)
            error(R.drawable.not_found)
        }

        tvSongNumber.text = song.id.toString()
        tvTitle.text = song.title
    }

    override fun getGroupCount(): Int {
        return albums.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return albums[groupPosition].songs.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return albums[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return albums[groupPosition].songs[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }
}
