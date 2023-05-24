package com.example.task.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.task.R
import com.example.task.adapters.PhotoAdapter
import com.example.task.viewmodel.NotesViewModel

class NotesFragment : Fragment() {

    private lateinit var  photoAdapter: PhotoAdapter
    private lateinit var  notesViewModel: NotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.mipmap.img1_foreground)
        context?.let {
            Glide.with(it)
                .load(R.mipmap.img1_foreground)
                .apply(RequestOptions().override(1000, 1000))
                .into(imageView)
        }
        val updateButton = view.findViewById<Button>(R.id.upgradeButton)
        updateButton.setBackgroundColor(resources.getColor(R.color.holo_orange_dark))

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        photoAdapter = PhotoAdapter(requireContext())
        val response = notesViewModel.getData()
        photoAdapter.setData(response.likes)
        recyclerView.adapter = photoAdapter
    }
}