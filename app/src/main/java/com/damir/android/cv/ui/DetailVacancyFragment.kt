package com.damir.android.cv.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.damir.android.cv.R
import com.damir.android.cv.VacancyRepository
import com.damir.android.cv.data.Salary
import com.damir.android.cv.data.VacancyDetail
import com.damir.android.cv.data.local.VacancyDetailLocal
import com.damir.android.cv.data.toLocal
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_vacancy_detail.*
import kotlinx.coroutines.launch

class DetailVacancyFragment : BaseFragment(R.layout.fragment_vacancy_detail) {

    companion object {
        private const val ARGS_ID = "Args#Id"
        fun create(id: Long): DetailVacancyFragment {
            val args = Bundle().apply {
                putLong(ARGS_ID, id)
            }
            return DetailVacancyFragment().apply {
                arguments = args
            }
        }
    }

    private val repo = VacancyRepository()
    private lateinit var delegate: Delegate
    private var vacancyDetailLocal: VacancyDetailLocal? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Delegate) delegate = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        coroutineScope.launch {
            showLoading()
            val id = arguments?.getLong(ARGS_ID) ?: 0L
            val vacancyDetail = repo.loadVacancyById(id)
            vacancyDetailLocal = vacancyDetail.toLocal()
            hideLoading()
            bindVacancyDetails(vacancyDetail)
        }
    }

    private fun setupToolbar() {
        val toolbar = view?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.inflateMenu(R.menu.menu_vacancy)
        toolbar?.setNavigationOnClickListener {
            delegate.onNavigationClicked()
        }
        toolbar?.setOnMenuItemClickListener {
            if(it.itemId == R.id.menu_save) {
                coroutineScope.launch {
                    repo.saveLocalVacancy(vacancyDetailLocal!!)
                    showSavedSnackbar()
                }
            }
            true
        }
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
        layoutDetails.visibility = View.GONE
    }

    private fun hideLoading() {
        progress.visibility = View.GONE
        layoutDetails.visibility = View.VISIBLE
    }

    private fun bindVacancyDetails(vacancy: VacancyDetail) {
        bindSalary(vacancy.salary)
        bindLogo(vacancy.employer?.logoUrls?.logo240)
        textCity.text = vacancy.address?.city
        textVacancyName.text = vacancy.name
        textExperience.text = vacancy.experience?.name
        textAddress.text = vacancy.address?.raw
        textDescription.loadDataWithBaseURL(null, vacancy.description, "text/html", "utf-8", null)
        whatsapp.setOnClickListener {
            delegate.onWhatsApp("")
        }
    }

    private fun bindSalary(salary: Salary?) {
        if(salary == null) {
            textSalary.visibility = View.GONE
        } else {
            val sal = when {
                salary.from == null && salary.to != null -> context?.getString(R.string.format_salary_to, salary.to, salary.currency)
                salary.from != null && salary.to == null -> context?.getString(R.string.format_salary_from, salary.from, salary.currency)
                salary.from != null && salary.to != null -> context?.getString(R.string.format_salary_full, salary.from, salary.to, salary.currency)
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

    private fun bindLogo(url: String?) {
        if(url == null) {
            imgLogo.visibility = View.GONE
        } else {
            imgLogo.visibility = View.VISIBLE
            Glide.with(this).load(url).into(imgLogo)
        }
    }

    private fun showSavedSnackbar() {
        Snackbar.make(requireView(), "Вакансия сохранена в избранное", Snackbar.LENGTH_SHORT).show()
    }

    interface Delegate {
        fun onWhatsApp(phone: String)
        fun onNavigationClicked()
    }
}