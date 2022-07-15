package com.example.ianklobe_musicapp.network

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ianklobe_musicapp.data.RandomSong
import com.example.ianklobe_musicapp.databinding.CardSongListBinding

class RandomSongAdapter(private val list: List<RandomSong>,
                        private val playSong: (RandomSong) -> Unit)
    : RecyclerView.Adapter<RandomSongAdapter.RandomSongViewHolder>() {


    // This sets up the recyclerview by including all the information needed in the card
    inner class RandomSongViewHolder(private val binding: CardSongListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(randomSong: RandomSong) {
            binding.tvAlbumName.text = randomSong.collectionName
            binding.tvSongName.text = randomSong.trackName
            binding.tvBandName.text = randomSong.artistName
            binding.tvPrice.text = randomSong.trackPrice.toString()

            Glide.with(binding.ivAlbumPicture)
                .load(randomSong.artworkUrl100)
                .into(binding.ivAlbumPicture)

            binding.root.setOnClickListener{
                playSong(randomSong)
            }
        }
    }

    // This will create the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomSongViewHolder {
        return RandomSongViewHolder(
            CardSongListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Connect the data to the view references
    override fun onBindViewHolder(holder: RandomSongViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    // Display as N elements in a list
    override fun getItemCount(): Int {
        return list.size
    }
}