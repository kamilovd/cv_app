package com.damir.android.cv.ui.resume

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.lifecycle.Observer
import com.damir.android.cv.R
import com.damir.android.cv.UserManager
import com.damir.android.cv.data.local.AppDatabase
import com.damir.android.cv.data.local.Resume
import com.damir.android.cv.ui.BaseFragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_resume.*
import kotlin.math.exp

class ResumeFragment : BaseFragment(R.layout.fragment_resume) {

    private val repo = AppDatabase.getDb().resumeDao()
    private val pieColors = listOf(R.color.red, R.color.yellow, R.color.green, R.color.blue)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = UserManager.component.user ?: "kamilov.damircr7@gmail.com"
        repo.getResumeByUser(user).observe(this, Observer {
            if(it != null) {
                showView()
                setupResume(it)
            }
            else hideView()
        })
    }

    private fun setupResume(resume: Resume) {
        val fullname = resume.name
        val phone = resume.phone
        val city = resume.city
        val speciality = resume.speciality
        val experience = resume.experience.split(",")
        val skills = resume.skills.split(",")

        fullName.text = fullname
        textspeciality.text = speciality
        textphone.text = phone
        textcity.text = city

        for(item in experience) {
            val expItemView = layoutInflater.inflate(R.layout.item_resume, layoutExperience, false)
            expItemView.findViewById<TextView>(R.id.label).text = item
            layoutExperience.addView(expItemView)
        }

        setPieChart(skills)
    }

    private fun setPieChart(skills: List<String>) {
        val listPie = ArrayList<PieEntry>()
        val listColors = ArrayList<Int>()
        for((index, item) in skills.withIndex()) {
            val parts = item.split("=")
            val s = parts[0]
            val p = parts[1]
            listPie.add(PieEntry(p.toFloat(), s))
            val colorIndex = if(index > 3) index%2 else index
            val color = pieColors[colorIndex]
            listColors.add(resources.getColor(color))
        }

        val pieDataSet = PieDataSet(listPie, "Навыки")
        pieDataSet.colors = listColors

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(15F)
        pieChartView.data = pieData
        pieChartView.setUsePercentValues(true)
        pieChartView.isDrawHoleEnabled = false
        pieChartView.description.isEnabled = false
        pieChartView.setEntryLabelColor(R.color.white)
        pieChartView.animateY(1400, Easing.EaseInOutQuad)
    }

    private fun hideView() {
        blockMainInfo.visibility = View.GONE
        blockExperience.visibility = View.GONE
        blockSkills.visibility = View.GONE
    }

    private fun showView() {
        blockMainInfo.visibility = View.VISIBLE
        blockExperience.visibility = View.VISIBLE
        blockSkills.visibility = View.VISIBLE
    }
}