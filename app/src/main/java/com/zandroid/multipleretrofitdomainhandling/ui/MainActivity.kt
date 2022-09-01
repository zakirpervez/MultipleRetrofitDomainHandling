package com.zandroid.multipleretrofitdomainhandling.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.zandroid.multipleretrofitdomainhandling.R
import com.zandroid.multipleretrofitdomainhandling.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedViewModel.let { Toast.makeText(this, "injection successful", Toast.LENGTH_SHORT).show() }
    }
}