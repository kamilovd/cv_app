package com.damir.android.cv.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.cv.R
import com.damir.android.cv.VacancyRepository
import com.damir.android.cv.data.Vacancy
import com.damir.android.cv.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_recommended.*
import kotlinx.coroutines.launch

class RecommendedFragment : BaseFragment(R.layout.fragment_recommended) {

    private val repo = VacancyRepository()
    private lateinit var adapter: RecommendedAdapter
    private lateinit var delegate: Delegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Delegate) delegate = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadVacancies()
    }

    private fun setupRecyclerView() {
        adapter = RecommendedAdapter(
            onSearchClicked = { delegate.onSearchClicked() },
            onItemClicked = { delegate.onItemClicked(it) }
        )
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recommendedRecycler)
        recyclerView?.adapter = adapter
    }

    private fun loadVacancies() {
        coroutineScope.launch {
            showProgress()
            val vacancies = repo.loadVacancies()
            updateItems(vacancies.items)
            hideProgress()
        }
    }

    private fun updateItems(items: List<Vacancy>) {
        adapter.setItems(items)
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        recommendedRecycler.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        recommendedRecycler.visibility = View.VISIBLE
    }

    interface Delegate {
        fun onSearchClicked()
        fun onItemClicked(id: Long)
    }
}