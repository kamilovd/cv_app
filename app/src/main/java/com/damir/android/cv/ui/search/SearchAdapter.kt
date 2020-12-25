package com.damir.android.cv.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.cv.R
import com.damir.android.cv.data.Vacancy

class SearchAdapter(
    private val onItemClicked: (id: Long) -> Unit
) : RecyclerView.Adapter<VacancyHolder>() {

    private val vacancies = mutableListOf<Vacancy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_vacancy, parent, false)
        return VacancyHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: VacancyHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.bind(vacancy)
    }

    override fun getItemCount(): Int = vacancies.size

    fun updateItems(items: List<Vacancy>) {
        vacancies.clear()
        vacancies.addAll(items)
        notifyDataSetChanged()
    }
}