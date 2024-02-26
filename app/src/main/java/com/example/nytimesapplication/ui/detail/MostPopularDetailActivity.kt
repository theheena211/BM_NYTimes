package com.example.nytimesapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nytimesapplication.R
import com.example.nytimesapplication.data.model.MostPopularArticle
import com.example.nytimesapplication.databinding.ActivityMostPopularDetailBinding
import com.example.nytimesapplication.utils.Constants

class MostPopularDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostPopularDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostPopularDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.detailToolbar.title = getString(R.string.article)
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        dispayData(intent?.getParcelableExtra(Constants.DETAIL_PARAM))
    }

    private fun dispayData(article: MostPopularArticle?) {
        article?.let {
            it.title?.let { binding.detailTitleTv.text = it }
            it.published_date?.let {
                binding.detailPublishon.text = Constants.PUBLISH_ON + Constants.SPACE + it
            }
            it.byline?.let { binding.detailByline.text = it }
            it.source?.let { binding.detailSourceTv.text = Constants.SOURCE + Constants.SPACE + it }
            it.abstract?.let { binding.detailDescriptionTv.text = it }
            it.section?.let { binding.detailSectionTv.text = Constants.DETAIL_SECTION + Constants.SPACE + it }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}