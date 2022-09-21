package com.zandroid.multipleretrofitdomainhandling.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView
import com.zandroid.multipleretrofitdomainhandling.R
import com.zandroid.multipleretrofitdomainhandling.api.DomainType
import com.zandroid.multipleretrofitdomainhandling.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var linearProgressIndicator: LinearProgressIndicator
    private lateinit var responseText: MaterialTextView
    private lateinit var domainType: DomainType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_acitivity)
        initializeViews()
        observeData()
        callApi()
    }

    private fun callApi() {
        if (domainType == DomainType.JSON_PLACE_HOLDER) {
            sharedViewModel.getTodoListFromJsonPlaceHolder()
        }else {
            sharedViewModel.getHelloWorldFromMockDomain()
        }
    }

    private fun observeData() {
        sharedViewModel.loadingLiveData.observe(this) {
            linearProgressIndicator.isVisible = it
            if (it) { responseText.text = getString(R.string.loading) }
        }

        sharedViewModel.errorLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            responseText.text = it
        }

        sharedViewModel.todoLiveData.observe(this) {
            responseText.text = it.string()
        }
        sharedViewModel.helloLiveData.observe(this) {
            responseText.text = it.string()
        }
    }

    private fun initializeViews() {
        linearProgressIndicator = findViewById(R.id.linear_progress_indicator)
        responseText = findViewById(R.id.response_text)
        domainType = intent.getSerializableExtra(DOMAIN_TYPE_EXTRA) as DomainType
    }

    companion object {
        private const val DOMAIN_TYPE_EXTRA = "domain_type_extra"
        fun getIntent(context: Context, type: DomainType): Intent {
            val intent = Intent(context, ApiActivity::class.java)
            intent.putExtra(DOMAIN_TYPE_EXTRA, type)
            return intent
        }
    }
}