package com.example.task.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.viewmodel.SharedViewModel

abstract class BaseFragment : Fragment() {

    protected lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
    }

    abstract fun setupUI(view: View)
    fun showError(view : View) {
        val fullView = view.findViewById<LinearLayout>(R.id.fullView)
        fullView.visibility = View.GONE
        val baseError = view.findViewById<TextView>(R.id.baseErrorView)
        fullView.visibility = View.VISIBLE
    }
}
