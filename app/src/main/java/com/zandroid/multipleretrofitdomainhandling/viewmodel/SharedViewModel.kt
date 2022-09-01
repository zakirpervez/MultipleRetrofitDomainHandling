package com.zandroid.multipleretrofitdomainhandling.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zandroid.multipleretrofitdomainhandling.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val loadingMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = loadingMutableLiveData
    private val errorMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = errorMutableLiveData
    private val todoMutableLiveData: MutableLiveData<ResponseBody> = MutableLiveData()
    val todoLiveData: LiveData<ResponseBody> = todoMutableLiveData
    private val helloMutableData: MutableLiveData<ResponseBody> = MutableLiveData()
    val helloLiveData: LiveData<ResponseBody> = helloMutableData

    fun getTodoListFromJsonPlaceHolder() = viewModelScope.launch(Dispatchers.IO) {
        try {
            loadingMutableLiveData.postValue(true)
            repository.getTodoListFromJsonPlaceHolder()
        } catch (e: Exception) {
            errorMutableLiveData.postValue(e.message)
        } finally {
            loadingMutableLiveData.postValue(false)
        }
    }

    fun getHelloWorldFromMockDomain() = viewModelScope.launch(Dispatchers.IO) {
        try {
            loadingMutableLiveData.postValue(true)
            repository.getHelloWorldFromMockDomain()
        } catch (e: Exception) {
            errorMutableLiveData.postValue(e.message)
        } finally {
            loadingMutableLiveData.postValue(false)
        }
    }
}
