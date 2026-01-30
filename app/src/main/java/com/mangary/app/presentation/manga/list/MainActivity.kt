package com.mangary.app.presentation.manga.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mangary.app.databinding.ActivityMainBinding
import com.mangary.app.domain.model.Manga
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity displaying list of manga
 * Part of presentation layer - handles UI interaction
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MangaListViewModel by viewModels()
    private lateinit var adapter: MangaListAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
        setupSwipeRefresh()
    }
    
    private fun setupRecyclerView() {
        adapter = MangaListAdapter { manga ->
            onMangaClick(manga)
        }
        
        binding.rvMangaList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }
    
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchManga(it)
                }
                return true
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                // Optional: implement real-time search
                return false
            }
        })
    }
    
    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.retry()
        }
    }
    
    private fun observeViewModel() {
        viewModel.mangaList.observe(this) { mangaList ->
            adapter.submitList(mangaList)
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
            binding.progressBar.visibility = if (isLoading && adapter.itemCount == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        
        viewModel.error.observe(this) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                binding.tvError.apply {
                    text = error
                    visibility = View.VISIBLE
                }
                binding.btnRetry.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        viewModel.retry()
                        visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                    }
                }
            } else {
                binding.tvError.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
            }
        }
    }
    
    private fun onMangaClick(manga: Manga) {
        // TODO: Navigate to manga detail screen
        Toast.makeText(this, "Selected: ${manga.title}", Toast.LENGTH_SHORT).show()
    }
}
