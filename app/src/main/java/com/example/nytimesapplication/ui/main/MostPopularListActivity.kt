package com.example.nytimesapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimesapplication.R
import com.example.nytimesapplication.data.model.MostPopularArticle
import com.example.nytimesapplication.databinding.ActivityMostPopularListBinding
import com.example.nytimesapplication.ui.detail.MostPopularDetailActivity
import com.example.nytimesapplication.utils.Constants

class MostPopularListActivity : AppCompatActivity(),ArticlesAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMostPopularListBinding
    private lateinit var adapter: ArticlesAdapter
    private val viewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostPopularListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        binding.toolbar.title = getString(R.string.main_screen_name)

        viewModel.articlesLiveData.observe(this, Observer { articles ->
            binding.progressBar.visibility = View.GONE
            articles?.results.let { adapter.submitList(it)
                binding.mprecyclerView.adapter=this.adapter
            }
        })
        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchMostPopularArticles()
    }

    private fun setUpRecyclerView(){
        adapter = ArticlesAdapter(null,this)
        binding.mprecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MostPopularListActivity)
            adapter = this@MostPopularListActivity.adapter
        }
    }

    override fun onItemClick(item: MostPopularArticle?) {
        item?.let {  val intent = Intent(this, MostPopularDetailActivity::class.java)
            intent.putExtra(Constants.DETAIL_PARAM, it)
            startActivity(intent) }

    }
}