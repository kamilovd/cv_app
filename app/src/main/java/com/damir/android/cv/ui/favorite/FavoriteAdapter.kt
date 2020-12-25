package com.damir.android.cv.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.damir.android.cv.R
import com.damir.android.cv.data.local.VacancyDetailLocal
import kotlinx.android.synthetic.main.item_vacancy_favorite.view.*

class FavoriteAdapter(
    private val onItemClicked: (id: String) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteVacancyHolder>() {

    private val vacancies = mutableListOf<VacancyDetailLocal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVacancyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_vacancy_favorite, parent, false)
        return FavoriteVacancyHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteVacancyHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.bind(vacancy)
    }

    override fun getItemCount(): Int = vacancies.size

    fun updateItems(items: List<VacancyDetailLocal>) {
        vacancies.clear()
        vacancies.addAll(items)
        notifyDataSetChanged()
    }


    inner class FavoriteVacancyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textName = itemView.textName
        private val textCity = itemView.textCity
        private val textSalary = itemView.textSalary
        private val imgLogo = itemView.imgLogo

        fun bind(vacancy: VacancyDetailLocal) {
            textCity.visibility = if(vacancy.city != null) View.VISIBLE else View.GONE
            imgLogo.visibility = if(vacancy.logo240 != null) View.VISIBLE else View.GONE
            bindSalary(vacancy)
            textName.text = vacancy.name
            textCity.text = vacancy.city
            Glide.with(itemView).load(vacancy.logo240).into(imgLogo)
            itemView.setOnClickListener { onItemClicked.invoke(vacancy.id) }
        }

        private fun bindSalary(vacancy: VacancyDetailLocal) {
            if(vacancy.from == null && vacancy.to == null) {
                textSalary.visibility = View.GONE
            } else {
                val sal = when {
                    vacancy.from == null && vacancy.to != null -> itemView.context.getString(R.string.format_salary_to, vacancy.to, vacancy.currency)
                    vacancy.from != null && vacancy.to == null -> itemView.context.getString(R.string.format_salary_from, vacancy.from, vacancy.currency)
                    vacancy.from != null && vacancy.to != null -> itemView.context.getString(R.string.format_salary_full, vacancy.from, vacancy.to, vacancy.currency)
                    else -> null
                }
                if(sal != null) {
                    textSalary.visibility = View.VISIBLE
                    textSalary.text = sal
                } else {
                    textSalary.visibility = View.GONE
                }
            }
        }
    }
}