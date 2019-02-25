package com.workshop.aroundme.app.ui.login


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.remote.service.UserRegisterItem

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.haveAccount).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.content_frame, RegisterFragment())?.commit()
        }

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val emailEditText = view.findViewById<EditText>(R.id.email)

        view.findViewById<Button>(R.id.registerButton).setOnClickListener {
            val userRepository = Injector.provideUserRepository(view.context)
            val user = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            userRepository.register(UserEntity(email), UserRegisterItem(user, email, password), ::onRegisterSuccessful, ::onRegisterFailed)
        }

        view.findViewById<TextView>(R.id.haveAccount).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.content_frame, LoginFragment())?.commit()
        }
    }

    private fun onRegisterFailed(error : String) {
        activity?.runOnUiThread {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.register_error))
                .setMessage(error)
                .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .create()
                .show()
        }
    }
    private fun onRegisterSuccessful(userEntity: UserEntity) {
        activity?.runOnUiThread {
            Toast.makeText(
                requireContext(),
                getString(R.string.register_success) + " " + userEntity.userName,
                Toast.LENGTH_LONG
            ).show()

            fragmentManager?.beginTransaction()
                ?.replace(R.id.content_frame, HomeFragment())
                ?.commit()
        }
    }
}
