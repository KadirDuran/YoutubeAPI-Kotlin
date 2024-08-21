package com.example.youtubeapi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.FragmentVideoBinding
import com.example.youtubeapi.databinding.VideorowBinding
import com.squareup.picasso.Picasso

class VideoInfoAdapter(val videoList: List<SearchResult>) : RecyclerView.Adapter<VideoInfoAdapter.SearchResultHolder>(){

    class SearchResultHolder(val binding: VideorowBinding) : RecyclerView.ViewHolder(binding.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder {
        val rcRowBinding  = VideorowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  SearchResultHolder(rcRowBinding)
    }

    override fun getItemCount(): Int {
      return videoList.size
    }

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.binding.txtTittle.text = videoList[position].snippet.title
        Picasso.get().load(videoList[position].snippet.thumbnails.default.url).into(holder.binding.imgVideo)
        holder.itemView.setOnClickListener {
            val action = videolistDirections.actionVideolistToVideo(videoList[position].snippet.thumbnails.default.url,videoList[position].snippet.title)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }
}