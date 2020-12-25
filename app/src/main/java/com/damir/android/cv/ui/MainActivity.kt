package com.damir.android.cv.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.damir.android.cv.R
import com.damir.android.cv.UserComponent
import com.damir.android.cv.UserManager
import com.damir.android.cv.hideKeyboard
import com.damir.android.cv.ui.favorite.FavoritesFragment
import com.damir.android.cv.ui.profile.CreateResumeFragment
import com.damir.android.cv.ui.profile.ProfileFragment
import com.damir.android.cv.ui.resume.ResumeFragment
import com.damir.android.cv.ui.search.RecommendedFragment
import com.damir.android.cv.ui.search.SearchDialogFragment
import com.damir.android.cv.ui.search.SearchFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    RecommendedFragment.Delegate, SearchDialogFragment.Delegate,
    SearchFragment.Delegate, DetailVacancyFragment.Delegate,
    ProfileFragment.Delegate, CreateResumeFragment.Delegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pushFragment(RecommendedFragment())

        saveUser()
        setupBottomNav()
    }

    override fun onSearchClicked() {
        pushFragmentBackStack(SearchDialogFragment())
        bottomNav.visibility = View.GONE
    }

    override fun onItemClicked(id: Long) {
        val fragment = DetailVacancyFragment.create(id)
        pushFragmentBackStack(fragment)
    }

    override fun onSearchRequested(text: String) {
        val fragment = SearchFragment.create(text)
        pushFragmentBackStack(fragment)
        bottomNav.visibility = View.VISIBLE
        hideKeyboard()
    }

    override fun onNavigationClicked() {
        popFragment()
    }

    override fun onWhatsApp(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: 87771421156"))
        startActivity(intent)
    }

    override fun onCreateResume() {
        pushFragmentBackStack(CreateResumeFragment())
    }

    override fun onResumeSaved() {
        popFragment()
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun pushFragmentBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(fragment.id.toString())
            .commit()
    }

    private fun popFragment() {
        supportFragmentManager
            .popBackStackImmediate()
    }

    private fun setupBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener {
            val fragment = when(it.itemId) {
                R.id.menu_search -> RecommendedFragment()
                R.id.menu_resume -> ResumeFragment()
                R.id.menu_favorites -> FavoritesFragment()
                R.id.menu_profile -> ProfileFragment()
                else -> RecommendedFragment()
            }
            pushFragment(fragment)
            true
        }
        bottomNav.setOnNavigationItemReselectedListener {
            return@setOnNavigationItemReselectedListener
        }
    }

    private fun saveUser() {
        val user = FirebaseAuth.getInstance().currentUser?.email
        UserManager.component.user = user
    }

    private fun selectBottomIcon(bottomIcon: BottomIcon) {
        bottomNav.selectedItemId = when(bottomIcon) {
            BottomIcon.SEARCH -> R.id.menu_search
            BottomIcon.RESUME -> R.id.menu_resume
            BottomIcon.FAVORITES -> R.id.menu_favorites
            BottomIcon.ACCOUNT -> R.id.menu_profile
        }
    }

    enum class BottomIcon {
        SEARCH,
        RESUME,
        FAVORITES,
        ACCOUNT
    }
}