package com.example.youtubeapi

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.youtubeapi.databinding.FragmentVideolistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class videolist : Fragment() {
    private  var API_KEY="AIzaSyBP24AnRFDtx5KBI8tY4tM4oI0oe94TR3s"
    private  var ItemList: ArrayList<SearchResult> = arrayListOf()
    private var _binding: FragmentVideolistBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        _binding = FragmentVideolistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            RetrofitClient()
           // val action =videolistDirections.actionVideolistToVideo("https://www.youtube.com/watch?v=S0Meh2scWl0","Türküler")
            //Navigation.findNavController(it).navigate(action)
        }
        binding.button2.setOnClickListener {
            Log.i("dsadasd", "onViewCreated: ${ItemList?.size.toString()}")
        }
    }

    private fun RetrofitClient() {
        val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        val apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoApi :: class.java)
        apiService.getSearchResults(API_KEY,"snippet","video","TR",10).enqueue(object : Callback<SearchListResponse> {
            override fun onResponse(
                call: Call<SearchListResponse>,
                response: Response<SearchListResponse>
            ) {
                if(response.isSuccessful)
                {
                    response.body()?.let {

                        for(item in it.items)
                        {
                            Log.i("items", " ${item.snippet.thumbnails.high.url}")
                            ItemList?.add(item)

                        }
                        Log.i("dsadasd", "onViewCreatedx: ${ItemList?.size.toString()}")
                    }
                }
            }

            override fun onFailure(call: Call<SearchListResponse>, t: Throwable) {
                Log.i("yyyyy","onfailure : ${t.message}")
            }

        })


    }
}