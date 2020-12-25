package com.damir.android.cv.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.damir.android.cv.R
import com.damir.android.cv.ui.BaseFragment
import com.damir.android.cv.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login_register.*

class RegisterFragment : BaseFragment(R.layout.fragment_login_register) {

    val auth = FirebaseAuth.getInstance()
    private val TAG = "RegisterFragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnRegister.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if(task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail: Success")
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d(TAG, "createUserWithEmail: Failed")
                }
            }
    }
}