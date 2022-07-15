package com.example.ianklobe_musicapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ianklobe_musicapp.data.MusicResponse
import com.example.ianklobe_musicapp.data.RandomSong
import com.example.ianklobe_musicapp.databinding.FragmentClassicBinding
import com.example.ianklobe_musicapp.databinding.FragmentPopBinding
import com.example.ianklobe_musicapp.network.ApiService
import com.example.ianklobe_musicapp.network.RandomSongAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopFragment : Fragment() {
    private lateinit var binding: FragmentPopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopBinding.inflate(inflater, container, false)

        fetchSongList("pop")

        return binding.root
    }

    // This gets the song list from the API through retrofit
    private fun fetchSongList(param: String) {
        ApiService.getRetrofitInstance().create(ApiService::class.java)
            .getSongLists(param).enqueue(object : Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    if (response.isSuccessful) {
                        val userAdapter = RandomSongAdapter(response.body()!!.results,
                            ::playSongFragment)
                        binding.rvPopList.adapter = userAdapter
                    }
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    // This will play the song on click through an implicit intent calling the youtube media player
    fun playSongFragment(randomSong: RandomSong) {
        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(Uri.parse(randomSong.previewUrl), "audio/mp4")
        startActivity(i)
    }
}
