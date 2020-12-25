package com.damir.android.cv.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.damir.android.cv.R
import com.damir.android.cv.VacancyRepository
import com.damir.android.cv.data.local.VacancyDetailLocal
import com.damir.android.cv.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private lateinit var adapter: FavoriteAdapter
    val repo = VacancyRepository()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        val recycler = recyclerFavorites
        adapter = FavoriteAdapter {}
        recycler.adapter = adapter
        repo.getAllSavedVacancy().observe(viewLifecycleOwner, Observer {
            updateItems(it)
        })
    }

    private fun updateItems(items: List<VacancyDetailLocal>) {
        adapter.updateItems(items)
    }

}