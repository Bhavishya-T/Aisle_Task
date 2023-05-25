package com.example.task.fragments

import android.content.ContentValues
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import com.example.task.NotesActivity
import com.example.task.R
import com.example.task.models.NotesResponse
import com.example.task.models.OtpLoginRequest
import com.example.task.models.OtpLoginResponse
import com.example.task.retrofit.ApiInterface
import com.example.task.retrofit.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EnterOtpFragment : BaseFragment() {
    override fun setupUI(view: View) {
        val title = view.findViewById<TextView>(R.id.title)
        title.text = sharedViewModel.phoneNumber.value

        val subtitle = view.findViewById<TextView>(R.id.subtitle)
        subtitle.setText("Enter the OTP")

        val continueButton = view.findViewById<Button>(R.id.continueButton)
        continueButton.setBackgroundColor(resources.getColor(R.color.holo_orange_dark))

        val codeLayout = view.findViewById<TextInputLayout>(R.id.dataField2)
        codeLayout.visibility = View.GONE

        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener(View.OnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.popBackStack()
        })

        val timer = view.findViewById<TextView>(R.id.timer)
        var count = 60
        val time = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.setText("0:"+count--)
            }

            override fun onFinish() {
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.popBackStack()
            }
        }
        time.start()

        val otpText = view.findViewById<TextInputEditText>(R.id.code)
        continueButton.setOnClickListener {
            val otp = otpText.text.toString()
            sharedViewModel.setOtp(otp)
            sharedViewModel.viewModelScope.launch(handler) {
                val response = sharedViewModel.loginWithOtp()
                if(response){
                    val intent = Intent(activity, NotesActivity::class.java)
                    startActivity(intent)
                }
                else{
                    showError(view)
                }
            }
        }
    }

    private val handler = CoroutineExceptionHandler { _, _ ->
        view?.let { showError(it) }
    }
}