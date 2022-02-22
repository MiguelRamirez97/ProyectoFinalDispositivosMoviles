package com.migue.proyectofinal.ui.resgister

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.migue.proyectofinal.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerBinding : FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(registerBinding){
            registerButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val namePlayer = namePLayerEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repPassword = repPasswordEditText.text.toString()

                if(email.isNotEmpty() && namePlayer.isNotEmpty() && password.isNotEmpty() && repPassword.isNotEmpty()){
                    if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                        if(password == repPassword){
                            if(password.length > 5){
                                //guardar en base de datos
                                findNavController().navigate(
                                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                                )
                            }else
                                Toast.makeText(requireContext(), "La contraseña debe contener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                        }else
                            Toast.makeText(requireContext(), "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                    }else
                        Toast.makeText(requireContext(), "Email invalido", Toast.LENGTH_SHORT).show()
                }else
                    Toast.makeText(requireContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

}