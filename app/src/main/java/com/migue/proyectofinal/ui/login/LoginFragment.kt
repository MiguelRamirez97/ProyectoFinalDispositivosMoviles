package com.migue.proyectofinal.ui.login

import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.migue.proyectofinal.databinding.FragmentLoginBinding
import com.migue.proyectofinal.models.Player

class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(loginBinding) {

            registerTextView.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            singInButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditTextLogin.text.toString()

                val player = Player(
                    namePlayer = "",
                    emailPlayer = email,
                    password = "",
                    bestScore = ""
                )

                if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                    if (email.isNotEmpty()) {//if (email == emailReceivedLogin && email.isNotEmpty()) {
                        if(password.isNotEmpty()) {//if(password == passwordReceivedLogin && password.isNotEmpty()) {
                            findNavController().navigate(
                              LoginFragmentDirections.actionLoginFragmentToStartingSelectionFragment(player)
                            )
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "La contraseña es incorrecta.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "La cuenta no se encuentra registrada.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Email invalido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
