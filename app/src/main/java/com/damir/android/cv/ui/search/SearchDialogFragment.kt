package com.damir.android.cv.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.damir.android.cv.R
import com.damir.android.cv.ui.BaseFragment

class SearchDialogFragment : BaseFragment(R.layout.fragmant_search_dialog) {

    private lateinit var delegate: Delegate
    private lateinit var searchView: SearchView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Delegate) delegate = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.searchView)
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null && query.length > 2) {
                    delegate.onSearchRequested(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    interface Delegate {
        fun onSearchRequested(text: String)
    }
}