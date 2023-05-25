package com.example.task

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.task.fragments.DiscoverFragment
import com.example.task.fragments.MatchesFragment
import com.example.task.fragments.NotesFragment
import com.example.task.fragments.ProfileFragment
import com.example.task.viewmodel.NotesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch


class NotesActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    private lateinit var  notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        lifecycleScope.launch(handler){
            val dialogJob = launch {
                delay(1000)
                try {
                    showLoadingDialog()
                    coroutineContext.job.join()
                } finally {
                    hideLoadingDialog()
                }
            }
            val result = notesViewModel.getLikesList()
            if(result){
                loadFragment(NotesFragment())
            }
            else{
                showErrorView()
            }
            dialogJob.cancel()
        }
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.discover -> {
                    loadFragment(DiscoverFragment())
                    true
                }

                R.id.matches -> {
                    loadFragment(MatchesFragment())
                    true
                }

                R.id.notes -> {
                    loadFragment(NotesFragment())
                    true
                }

                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private val handler = CoroutineExceptionHandler { _, _ ->
        showErrorView()
    }

    private fun showErrorView() {
        val mainView = findViewById<BottomNavigationView>(R.id.bottomNav)
        mainView.visibility = View.GONE
        val errorView = findViewById<TextView>(R.id.activityErrorView)
        errorView.visibility = View.VISIBLE
    }

    private fun hideLoadingDialog() {
        var loader = findViewById<ProgressBar>(R.id.loading)
        loader.visibility = View.GONE
    }

    private fun showLoadingDialog() {
        var loader = findViewById<ProgressBar>(R.id.loading)
        loader.visibility = View.VISIBLE
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}