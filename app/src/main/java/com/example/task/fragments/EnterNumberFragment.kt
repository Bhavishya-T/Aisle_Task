package com.example.task.fragments

import android.opengl.Visibility
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import com.example.task.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class EnterNumberFragment : BaseFragment() {

    override fun setupUI(view: View) {
        val continueButton = view.findViewById<Button>(R.id.continueButton)
        continueButton.setBackgroundColor(resources.getColor(R.color.holo_orange_dark))

        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        imageButton.visibility = View.GONE

        val timer = view.findViewById<TextView>(R.id.timer)
        timer.visibility = View.GONE

        val phoneNumber = view.findViewById<TextInputEditText>(R.id.number)
        phoneNumber.hasFocus()

        continueButton.setOnClickListener {
            val data = "+91"+phoneNumber.text.toString()
            sharedViewModel.setData(data)
            sharedViewModel.viewModelScope.launch(handler){
                val response = sharedViewModel.login()
                if(response){
                    goToNextScreen()
                }
                else{
                    showError(view)
                }
            }
        }
    }

    private fun goToNextScreen(){
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, EnterOtpFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private val handler = CoroutineExceptionHandler { _, _ ->
        view?.let { showError(it) }
    }
}