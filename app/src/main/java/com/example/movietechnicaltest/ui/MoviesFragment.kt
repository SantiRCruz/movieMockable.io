package com.example.movietechnicaltest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietechnicaltest.R
import com.example.movietechnicaltest.core.networkInfo
import com.example.movietechnicaltest.databinding.FragmentMoviesBinding
import com.example.movietechnicaltest.presentation.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import com.example.movietechnicaltest.core.Result
import com.example.movietechnicaltest.core.hide
import com.example.movietechnicaltest.core.show
import com.example.movietechnicaltest.data.models.BaseResponse
import com.example.movietechnicaltest.data.models.movies.MoviesResponse
import com.example.movietechnicaltest.data.rest.RetrofitClient
import com.example.movietechnicaltest.domain.movies.MoviesRepoImpl
import com.example.movietechnicaltest.presentation.MoviesViewModelFactory
import com.example.movietechnicaltest.ui.adapters.MoviesAdapter

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel by viewModels<MoviesViewModel> {
        MoviesViewModelFactory(
            MoviesRepoImpl(
                RetrofitClient.webService
            )
        )
    }
    private var moviesList = listOf<MoviesResponse>()
    private var baseMovieList = listOf<MoviesResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        fetchMovies()
        clickListeners()

    }

    private fun clickListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            fetchMovies()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == ""){
                    moviesList = baseMovieList
                    setUpMovies()
                }else{
                    moviesList = baseMovieList.filter { it.title.contains(newText.toString().uppercase()) }
                    setUpMovies()
                }
                return true
            }
        })

        binding.llAlert.setOnClickListener {
            binding.llAlert.hide()
            fetchMovies()
        }
    }

    private fun fetchMovies() {
        if (!networkInfo(requireContext())) {
            alertMessage("No encontramos conexión a internet para descargar la información\n\n Presione para volver a intentar ")
        } else {
            binding.progress.show()
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getMovies().collect {
                        when (it) {
                            is Result.Loading -> {}
                            is Result.Success -> {
                                if (binding.swipeRefresh.isRefreshing) {
                                    binding.swipeRefresh.isRefreshing = false
                                }
                                binding.progress.hide()
                                Log.e("fetchMovies: ", it.toString())
                                baseMovieList = it.data.results.shuffled()
                                moviesList = baseMovieList
                                setUpMovies()
                            }

                            is Result.Failure -> {
                                Snackbar.make(
                                    binding.root,
                                    "Error al obtener los datos",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                Log.e("Error", "fetchMovies: ${it.exception.message}")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpMovies() {
        val adapter = MoviesAdapter(moviesList, onClickItem = {onClickMovie(it)})
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter
    }

    private fun onClickMovie(it: MoviesResponse) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(it)
        Navigation.findNavController(binding.root).navigate(action)
    }

    private fun alertMessage(s: String) {
        binding.txtAlert.text = s
        binding.llAlert.animate().translationY(300f).alpha(0f).setDuration(0).withEndAction {
            binding.llAlert.visibility = View.VISIBLE
            binding.llAlert.animate().translationY(0f).alpha(1f).duration = 600
        }
    }

}