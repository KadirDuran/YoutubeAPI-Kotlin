package com.example.youtubeapi

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.databinding.FragmentVideolistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class videolist : Fragment() {
    private  var API_KEY="AIzaSyCqkBmw6TDLFIu6wjmw4ilx1xVE8t50NpQ"
    private val BASE_URL = "https://www.googleapis.com/youtube/v3/"
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
        GetDataAndShow()
        binding.button.setOnClickListener {  GetDataAndShow()  }
    }
    fun GetDataAndShow() {
        lifecycleScope.launch {
            try {
                val videoInfoList = withContext(Dispatchers.IO) {
                    getDataRetrofit()
                }
                withContext(Dispatchers.Main) {
                    val adapter = VideoInfoAdapter(videoInfoList)
                    binding.rcrow.adapter = adapter
                    binding.rcrow.layoutManager = LinearLayoutManager(requireContext())
                }
            } catch (e: Exception) {
                Log.e("ThreadState", "Hata: ${e.message}", e)
            }
        }
    }
    private suspend fun getDataRetrofit(): List<SearchResult> {
         val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         val apiService = retrofit.create(VideoApi::class.java)

        return try {
            val response = apiService.getSearchResults(API_KEY, "snippet", "video", "TR", 10)
            response.items
        } catch (e: Exception) {
            Log.e("RetrofitError", "Error fetching data: ${e.message}", e)
            emptyList()
        }
    }
}