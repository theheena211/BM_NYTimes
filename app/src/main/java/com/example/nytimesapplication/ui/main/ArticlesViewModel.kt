package com.example.nytimesapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesapplication.data.model.MostPopularResponse
import com.example.nytimesapplication.data.remote.NYTimesRepository
import com.example.nytimesapplication.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    private val mRepository: NYTimesRepository
    private val _articlesLiveData = MutableLiveData<MostPopularResponse?>()
    val articlesLiveData: LiveData<MostPopularResponse?> = _articlesLiveData

    init {
        mRepository = NYTimesRepository()
    }

    private val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        exception.message?.let { Log.d("Error", it) }
        handleError()
    }

    fun fetchMostPopularArticles() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = mRepository.getMostPopularArticles(
                Constants.SECTION,
                Constants.PERIOD,
                Constants.API_KEY
            )
            val articles: MostPopularResponse = response
            _articlesLiveData.postValue(articles)
        }
    }

    private fun handleError() {
        _articlesLiveData.postValue(null)
    }
}