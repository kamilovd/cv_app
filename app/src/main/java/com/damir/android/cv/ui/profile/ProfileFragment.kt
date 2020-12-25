package com.damir.android.cv.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.damir.android.cv.R
import com.damir.android.cv.ui.BaseFragment
import com.damir.android.cv.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    private lateinit var delegate: Delegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        delegate = context as Delegate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.inflateMenu(R.menu.menu_profile)
        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.menu_signout) {
                signOut()
            }
            true
        }
        bindProfileData()
        btnCreateResume.setOnClickListener { delegate.onCreateResume() }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun bindProfileData() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            val phoneNumber = user.phoneNumber
            textNickname.text = name
            textEmail.text = email
            textPhoneNumber.text = phoneNumber
            Glide.with(this).load(photoUrl).into(imgAvatar)
        }
    }

    interface Delegate {
        fun onCreateResume()
    }
}