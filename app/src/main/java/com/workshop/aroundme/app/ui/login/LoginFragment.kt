package com.workshop.aroundme.app.ui.login


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.data.UserRepository
import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.remote.service.UserLoginItem
import kotlinx.android.synthetic.main.fragment_login.*
import java.lang.Exception

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ApplySharedPref")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)

        view.findViewById<View>(R.id.register).setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.content_frame, RegisterFragment())
                ?.commit()
        }
        view.findViewById<View>(R.id.login).setOnClickListener {

            val userRepository = Injector.provideUserRepository(view.context)
            val user = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val rememberFlag = view.findViewById<CheckBox>(R.id.rememberCheckBox).isChecked

            userRepository.login(UserEntity(user), UserLoginItem(user, password), rememberFlag, ::onLoginSuccessful, ::onLoginFailed)

        }
    }

    private fun onLoginFailed(error : String) {
        activity?.runOnUiThread {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.login_error))
                .setMessage(error)
                .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .create()
                .show()
        }
    }
    private fun onLoginSuccessful(userEntity: UserEntity) {
        activity?.runOnUiThread {
            Toast.makeText(
                requireContext(),
                getString(R.string.login_success) + " " + userEntity.userName,
                Toast.LENGTH_LONG
            ).show()

            fragmentManager?.beginTransaction()
                ?.replace(R.id.content_frame, HomeFragment())
                ?.commit()
        }
    }

}
