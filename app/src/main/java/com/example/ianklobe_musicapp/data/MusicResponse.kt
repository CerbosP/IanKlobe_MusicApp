package com.example.ianklobe_musicapp.data

// All the data needed from the API for the code to function

data class MusicResponse(
    val results: List<RandomSong>
)

data class RandomSong(
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val artworkUrl100: String,
    val previewUrl: String,
    val trackPrice: Double
)

/*
    artistName
    collectionName
    artworkUrl60
    trackPrice
 */
