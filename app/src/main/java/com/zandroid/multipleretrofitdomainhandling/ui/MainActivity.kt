package com.zandroid.multipleretrofitdomainhandling.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.zandroid.multipleretrofitdomainhandling.R
import com.zandroid.multipleretrofitdomainhandling.api.DomainType
import com.zandroid.multipleretrofitdomainhandling.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var jsonPlaceHolderApiButton: MaterialButton
    private lateinit var mockableIOApiButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeData()
        initializeView()
        setupClickEvents()
    }

    private fun observeData() {
        sharedViewModel.domainChangeLiveData.observe(this) {
            Toast.makeText(this, "$it selected", Toast.LENGTH_SHORT).show()
            startActivity(ApiActivity.getIntent(this, it))
        }
    }

    private fun initializeView() {
        jsonPlaceHolderApiButton = findViewById(R.id.json_place_holder_api_button)
        mockableIOApiButton = findViewById(R.id.mockable_io_api_button)
    }

    private fun setupClickEvents() {
        jsonPlaceHolderApiButton.setOnClickListener {
            sharedViewModel.changeDomain(DomainType.JSON_PLACE_HOLDER)
        }
        mockableIOApiButton.setOnClickListener {
            sharedViewModel.changeDomain(DomainType.MOCK)
        }
    }
}