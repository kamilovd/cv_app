package com.damir.android.cv.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.cv.R
import com.damir.android.cv.data.Vacancy
import com.google.android.material.button.MaterialButton

class RecommendedAdapter(
    private val onSearchClicked: () -> Unit,
    private val onItemClicked: (id: Long) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val types = arrayOf(R.layout.item_searchview, R.layout.item_header, R.layout.item_vacancy, R.layout.item_button)
    private val vacancies = mutableListOf<Vacancy>()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> types[0]
            1 -> types[1]
            vacancies.lastIndex -> types[3]
            else -> types[2]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)
        return when(viewType) {
            types[0] -> SearchViewHolder(view)
            types[1] -> HeaderHolder(view)
            types[3] -> ButtonHolder(view)
            else -> VacancyHolder(view, onItemClicked)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SearchViewHolder -> { holder.bind() }
            is ButtonHolder -> { holder.bind() }
            is VacancyHolder -> {
                val vacancy = vacancies[position]
                holder.bind(vacancy)
            }
        }
    }

    override fun getItemCount(): Int = vacancies.size

    fun setItems(items: List<Vacancy>) {
        vacancies.clear()
        vacancies.addAll(items)
        notifyDataSetChanged()
    }

    inner class HeaderHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}
    inner class ButtonHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val buttonShowMore = itemView.findViewById<MaterialButton>(R.id.buttonShowMore)
        fun bind() { buttonShowMore.setOnClickListener {} }
    }
    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val searchView = itemView.findViewById<TextView>(R.id.searchView)
        fun bind() { searchView.setOnClickListener { onSearchClicked.invoke() } }
    }
}