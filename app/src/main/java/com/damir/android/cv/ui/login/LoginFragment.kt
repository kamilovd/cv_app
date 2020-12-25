package com.damir.android.cv.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.damir.android.cv.R
import com.damir.android.cv.ui.BaseFragment
import com.damir.android.cv.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login_login.*
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.fragment_login_register.editEmail
import kotlinx.android.synthetic.main.fragment_login_register.editPassword

class LoginFragment : BaseFragment(R.layout.fragment_login_login) {

    val auth = FirebaseAuth.getInstance()
    private val TAG = "LoginFragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_login.setOnClickListener {
            login()
        }
        textForgetPassword.setOnClickListener {
            resetPass()
        }
    }

    private fun login() {
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if(task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail: Success")
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d(TAG, "signInWithEmail: Failed")
                }
            }
    }

    private fun resetPass() {
        val email = "d.kamilov29@gmail.com"
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener() {
                if(it.isSuccessful) {
                    showSnack()
                }
            }
    }

    private fun showSnack() {
        Snackbar.make(requireView().rootView, "Письмо отправлено на вашу почту", Snackbar.LENGTH_SHORT).show()
    }
}