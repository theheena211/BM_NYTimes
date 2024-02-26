package com.example.nytimesapplication

import com.example.nytimesapplication.data.model.MostPopularArticle
import com.example.nytimesapplication.data.model.MostPopularResponse
import com.example.nytimesapplication.data.remote.NYTimesRepository
import com.example.nytimesapplication.data.remote.RetrofitInstance
import com.example.nytimesapplication.network.api.NYTimesApi
import com.example.nytimesapplication.ui.main.ArticlesViewModel
import com.example.nytimesapplication.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.mockito.MockitoAnnotations

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class ArticlesViewModelTest {

    private val testCoroutineScope = TestScope()

    @Mock
    private lateinit var repository: NYTimesRepository

    @Mock
    private lateinit var api: NYTimesApi

    private lateinit var viewModel: ArticlesViewModel

    private lateinit var response: MostPopularResponse

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = ArticlesViewModel()
        val list: List<MostPopularArticle>
        list = listOf()
        response = MostPopularResponse(list)
    }

    private suspend fun mockingAPIcall() {
        repository.api = RetrofitInstance.api
        `when`(
            repository.getMostPopularArticles(
                Constants.SECTION,
                Constants.PERIOD,
                Constants.API_KEY
            )
        ).thenReturn(
            response
        )
    }

    @Test
    fun testFetchMostPopularArticles() {
        testCoroutineScope.runTest {
            mockingAPIcall()
            viewModel.fetchMostPopularArticles()
            Assert.assertEquals(response.results?.size, 0)
        }
    }
}