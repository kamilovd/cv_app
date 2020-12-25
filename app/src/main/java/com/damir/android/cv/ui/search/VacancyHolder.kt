package com.damir.android.cv.ui.search

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.cv.DateUtils
import com.damir.android.cv.R
import com.damir.android.cv.data.Contacts
import com.damir.android.cv.data.Employer
import com.damir.android.cv.data.Salary
import com.damir.android.cv.data.Vacancy
import com.damir.android.cv.toHtml

class VacancyHolder(
    itemView: View,
    private val onItemClicked: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

    private val textVacancyName = itemView.findViewById<TextView>(R.id.textVacancyName)
    private val textCity = itemView.findViewById<TextView>(R.id.textCity)
    private val textSalary = itemView.findViewById<TextView>(R.id.textSalary)
    private val textEmployer = itemView.findViewById<TextView>(R.id.textEmployer)
    private val textResponsibility = itemView.findViewById<TextView>(R.id.textResponsibility)
    private val textRequirements = itemView.findViewById<TextView>(R.id.textRequirements)
    private val textDate = itemView.findViewById<TextView>(R.id.textDate)
    private val textContacts = itemView.findViewById<TextView>(R.id.textContacts)
    private val buttonAddToFavorites = itemView.findViewById<ImageView>(R.id.buttonAddToFavorites)

    fun bind(vacancy: Vacancy) {
        textVacancyName.text = vacancy.name
        bindSalary(vacancy.salary)
        bindEmployer(vacancy.employer)
        bindDate(vacancy.publishedAt)
        bindCity(vacancy.address?.city)
        bindResponsibility(vacancy.snippet?.responsibility)
        bindRequirements(vacancy.snippet?.requirement)
        bindContacts(vacancy.contacts)
        buttonAddToFavorites.setOnClickListener {
            
        }
        itemView.setOnClickListener {
            onItemClicked(vacancy.id)
        }
    }

    private fun bindSalary(salary: Salary?) {
        if(salary == null) {
            textSalary.visibility = View.GONE
        } else {
            val sal = when {
                salary.from == null && salary.to != null -> itemView.context.getString(R.string.format_salary_to, salary.to, salary.currency)
                salary.from != null && salary.to == null -> itemView.context.getString(R.string.format_salary_from, salary.from, salary.currency)
                salary.from != null && salary.to != null -> itemView.context.getString(R.string.format_salary_full, salary.from, salary.to, salary.currency)
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

    private fun bindCity(city: String?) {
        if(city == null) {
            textCity.visibility = View.GONE
        } else {
            textCity.visibility = View.VISIBLE
            textCity.text = city
        }
    }

    private fun bindEmployer(employer: Employer?) {
        if(employer == null) {
            textEmployer.visibility = View.GONE
        } else {
            if(employer.name != null) {
                textEmployer.visibility = View.VISIBLE
                if(employer.trusted == true) {
                    textEmployer.text = itemView.context.getString(R.string.format_company_trusted, employer.name)
                } else {
                    textEmployer.text = employer.name
                }
            }
        }
    }

    private fun bindRequirements(requirements: String?) {
        if(requirements == null) {
            textRequirements.visibility = View.GONE
        } else {
            textRequirements.visibility = View.VISIBLE
            textRequirements.text = requirements.toHtml()
        }
    }

    private fun bindResponsibility(responsibility: String?) {
        if(responsibility == null) {
            textResponsibility.visibility = View.GONE
        } else {
            textResponsibility.visibility = View.VISIBLE
            textResponsibility.text = responsibility.toHtml()
        }
    }

    private fun bindDate(date: String?) {
        if(date == null) {
            textDate.visibility = View.GONE
        } else {
            val dateString = date.take(10)
            textDate.visibility = View.VISIBLE
            textDate.text = DateUtils.format(dateString)
        }
    }

    private fun bindContacts(contacts: Contacts?) {
        if(contacts == null) {
            textContacts.visibility = View.GONE
            textContacts.setOnClickListener {

            }
        } else {
            textContacts.visibility = View.VISIBLE
        }
    }
}