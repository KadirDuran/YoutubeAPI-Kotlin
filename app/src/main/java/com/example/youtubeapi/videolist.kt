package com.example.youtubeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.youtubeapi.databinding.FragmentVideolistBinding

class videolist : Fragment() {
    private  var API_KEY=""
    private  var CATEGORY_NAME=""
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
            val action =videolistDirections.actionVideolistToVideo("https://www.youtube.com/watch?v=S0Meh2scWl0","Türküler")
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun FindCategory()
    {
        var query = "https://www.googleapis.com/youtube/v3/search?key="+API_KEY+"&q="+CATEGORY_NAME+"&part=snippet&type=video&maxResults=10"

    }
}