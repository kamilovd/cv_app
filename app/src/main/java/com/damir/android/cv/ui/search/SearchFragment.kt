package com.damir.android.cv.ui.search

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.cv.R
import com.damir.android.cv.VacancyRepository
import com.damir.android.cv.data.Vacancy
import com.damir.android.cv.ui.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    companion object {
        private const val ARGS_QUERY = "Args#query"
        fun create(text: String): SearchFragment {
            val args = Bundle().apply {
                putString(ARGS_QUERY, text)
            }
            return SearchFragment().apply {
                arguments = args
            }
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: SearchAdapter
    private lateinit var delegate: Delegate
    private val repo = VacancyRepository()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Delegate) delegate = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerSearch)
        toolbar = view.findViewById(R.id.toolbar)
        val query = arguments?.getString(ARGS_QUERY) ?: ""
        toolbar.title = query
        setupAdapter()
        loadVacancies(query)
    }

    private fun setupAdapter() {
        adapter = SearchAdapter { delegate.onItemClicked(it) }
        recyclerView.adapter = adapter
    }

    private fun loadVacancies(query: String) {
        coroutineScope.launch {
            val vacancies = repo.loadVacancies(query)
            val items = if(TextUtils.isDigitsOnly(query)) {
                val queryDigits = query.toLong()
                vacancies.items.filter { vacancy ->
                    vacancy.salary?.from?.let {
                        it > queryDigits || it == queryDigits
                    } ?: false
                }
            } else {
                vacancies.items
            }
            adapter.updateItems(items)
        }
    }

    interface Delegate {
        fun onItemClicked(id: Long)
    }
}