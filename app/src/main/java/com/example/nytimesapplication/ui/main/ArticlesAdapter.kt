package com.example.nytimesapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesapplication.data.model.MostPopularArticle
import com.example.nytimesapplication.data.model.MostPopularResponse
import com.example.nytimesapplication.databinding.ItemArticleBinding

class ArticlesAdapter(
    var articles: List<MostPopularArticle>?,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles?.get(position)
        holder.bind(article)
    }

    fun submitList(articleList: List<MostPopularArticle>?) {
        articles = articleList
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(articles?.get(position))
            }
        }

        fun bind(article: MostPopularArticle?) {
            article?.title?.let { binding.titleTv.text = it }
            article?.published_date?.let { binding.publishedDateTv.text = it }
            article?.byline?.let { binding.bylineTv.text = it }
            article?.location?.let {
                if (it.isNotEmpty()) {
                    binding.locationTv.text = it[0]
                }
            }

        }


    }

    interface OnItemClickListener {
        fun onItemClick(item: MostPopularArticle?)
    }

}