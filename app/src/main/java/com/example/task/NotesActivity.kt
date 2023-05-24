package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.task.fragments.DiscoverFragment
import com.example.task.fragments.MatchesFragment
import com.example.task.fragments.NotesFragment
import com.example.task.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class NotesActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        loadFragment(NotesFragment())
        bottomNav =  findViewById(R.id.bottomNav)
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
                else -> {true}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}