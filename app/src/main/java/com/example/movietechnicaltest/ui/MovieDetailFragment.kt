package com.example.movietechnicaltest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movietechnicaltest.R
import com.example.movietechnicaltest.core.htmlFormat
import com.example.movietechnicaltest.databinding.FragmentMovieDetailBinding
import com.example.movietechnicaltest.ui.adapters.MoviesAdapter
import com.example.movietechnicaltest.ui.adapters.PersonAdapter

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding

    private val args by navArgs<MovieDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieDetailBinding.bind(view)
        setData()
        clicks()
    }

    private fun clicks() {
        binding.ivPrevious.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setData() {
        Glide.with(requireContext())
            .load(args.movie.movieImages.frameMoviePath)
            .into(binding.ivBackground)

        Glide.with(requireContext())
            .load(args.movie.movieImages.posterPath)
            .into(binding.ivPoster)

        var genders = ""

        args.movie.gender.forEachIndexed() { index, it ->
            genders += if (args.movie.gender.size-1 == index )"${it.name}." else "${it.name},"
        }

        binding.textViewTitle.text = "🎬 ${ args.movie.title } "
        binding.textviewFeatures.text =
            ("⭐️ <b><FONT COLOR=\\\"yellow\\\">${args.movie.popularityQualification}%</FONT></b> ⏱️ ${args.movie.duration} 🗓️ ${args.movie.releaseYear}<br><br>" +
                    "<b>Generos: </b> $genders").htmlFormat()
        binding.textviewSynopsis.text = "<b>Sinopsis: </b><br><br>${args.movie.synopsis}".htmlFormat()

        setUpActors()
        setUpDirectors()
    }

    private fun setUpActors() {
        val adapter = PersonAdapter(args.movie.actors)
        binding.rvActors.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvActors.adapter = adapter
    }

    private fun setUpDirectors() {
        val adapter = PersonAdapter(args.movie.directedBy)
        binding.rvDirectors.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvDirectors.adapter = adapter
    }


}