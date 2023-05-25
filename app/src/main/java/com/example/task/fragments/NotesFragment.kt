package com.example.task.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.task.R
import com.example.task.adapters.PhotoAdapter
import com.example.task.models.NotesResponse
import com.example.task.viewmodel.NotesViewModel

class NotesFragment : Fragment() {

    private lateinit var  photoAdapter: PhotoAdapter
    private lateinit var notesViewModel : NotesViewModel
    private lateinit var response : NotesResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        response = notesViewModel.getData()
        setupImage()
        setupRecyclerView()

        val updateButton = view.findViewById<Button>(R.id.upgradeButton)
        updateButton.setBackgroundColor(resources.getColor(R.color.holo_orange_dark))
    }

    private fun setupRecyclerView() {
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        photoAdapter = PhotoAdapter(requireContext())
        photoAdapter.setData(response.likes)
        recyclerView.adapter = photoAdapter    }

    private fun setupImage() {
        var imageView = view!!.findViewById<ImageView>(R.id.imageView)
        Log.d(ContentValues.TAG,"Response Invites" +response.invites)
        Log.d(ContentValues.TAG,"Response Invites" +response.invites.profiles[0])
        Log.d(ContentValues.TAG,"Response Invites" +response.invites.profiles[0].photos[0])
        Log.d(ContentValues.TAG,"Response Invites" +response.invites.profiles[0].photos[0].photo)
        context?.let {
            Glide.with(it)
                .load(response.invites.profiles[0].photos[0].photo)//current.avatar, test data
                .apply(RequestOptions().override(1000, 1000))
                .into(imageView)
        }

        val notesText = view!!.findViewById<TextView>(R.id.notesName)
        Log.d(ContentValues.TAG,"Name "+response.invites.profiles[0].generalInformation.name)
        notesText.setText(response.invites.profiles[0].generalInformation.name+", "+response.invites.profiles[0].generalInformation.age)
    }
}