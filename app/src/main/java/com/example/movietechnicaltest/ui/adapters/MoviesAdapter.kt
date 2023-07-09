package com.example.movietechnicaltest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietechnicaltest.data.models.movies.MoviesResponse
import com.example.movietechnicaltest.databinding.ItemMovieBinding

class MoviesAdapter(
    private val list: List<MoviesResponse>,
    private val onClickItem: (MoviesResponse) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list[position],onClickItem)
    }

    class MoviesViewHolder(private val binding: ItemMovieBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviesResponse,onClickItem: (MoviesResponse) -> Unit) {
            Glide.with(context)
                .load(item.movieImages.posterPath)
                .into(binding.ivPoster)
            binding.textViewTitle.text = item.title
            binding.textViewTime.text = item.duration
            binding.textviewYear.text = item.releaseYear
            binding.root.setOnClickListener { onClickItem(item) }
        }
    }
}