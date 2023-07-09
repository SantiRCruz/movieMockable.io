package com.example.movietechnicaltest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietechnicaltest.data.models.movies.Person
import com.example.movietechnicaltest.databinding.ItemPersonBinding

class PersonAdapter(
    private val list: List<Person>,
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        val itemBinding = ItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class PersonViewHolder(private val binding: ItemPersonBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person) {
            Glide.with(context)
                .load(item.portraitPath)
                .into(binding.ivPoster)
            binding.textViewName.text = item.name
        }
    }
}