package com.damir.android.cv.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import com.damir.android.cv.R
import com.damir.android.cv.UserManager
import com.damir.android.cv.data.local.AppDatabase
import com.damir.android.cv.data.local.Resume
import com.damir.android.cv.ui.BaseFragment
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_resume.*
import kotlinx.coroutines.launch

class CreateResumeFragment : BaseFragment(R.layout.fragment_create_resume) {

    private val dao = AppDatabase.getDb().resumeDao()
    private lateinit var delegate: Delegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        delegate = context as Delegate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSaveResume.setOnClickListener { saveResume() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAddExperience.setOnClickListener {
            layoutExperience.addView(EditText(ContextThemeWrapper(requireContext(), R.style.CV_EditText)))
        }
        btnAddSkill.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.widget_skill, layoutSkills, false)
            layoutSkills.addView(view)
        }
    }

    private fun saveResume() {
        val name = editName.text.toString()
        val phone = editPhone.text.toString()
        val city = editCity.text.toString()
        val speciality = editSpeciality.text.toString()

        val skillCount = layoutSkills.childCount
        val skills = mutableListOf<String>()
        for(i in 1 until skillCount) {
            val child = layoutSkills.getChildAt(i) as LinearLayout
            val s = child.findViewById<EditText>(R.id.editSkill).text.toString()
            val p = child.findViewById<EditText>(R.id.editPercent).text.toString()
            val skill = "$s=$p"
            skills.add(skill)
        }
        val skillString = skills.joinToString(",")

        val expCount = layoutExperience.childCount
        val experience = mutableListOf<String>()
        for(i in 1 until expCount) {
            val child = layoutExperience.getChildAt(i) as EditText
            experience.add(child.text.toString())
        }
        val experienceString = experience.joinToString(",")

        val user = UserManager.component.user ?: "kamilov.damircr7@gmail.com"
        val resume = Resume(user, name, phone, city, speciality, skillString, experienceString)
        coroutineScope.launch {
            dao.saveResume(resume)
            Snackbar.make(requireView(), "Резюме сохранено", Snackbar.LENGTH_SHORT).show()
            delegate.onResumeSaved()
        }

    }

    interface Delegate {
        fun onResumeSaved()
    }
}