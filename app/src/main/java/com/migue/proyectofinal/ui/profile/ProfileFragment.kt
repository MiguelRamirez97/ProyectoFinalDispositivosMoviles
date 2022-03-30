package com.migue.proyectofinal.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.migue.proyectofinal.databinding.FragmentProfileBinding
import com.migue.proyectofinal.server.PlayerServer

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profileBinding: FragmentProfileBinding

    val  REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

       with(profileBinding) {

           profileViewModel.searchProfileFromServerDone.observe(viewLifecycleOwner){result ->
               if (result != null) {
                   onSearchProfileFromServerDone(result)
               }
           }

           profileViewModel.findPlayer()

           imageProfile.setOnClickListener{
               dispatchTakePictureIntent()
           }
       }
    }

    private fun onSearchProfileFromServerDone(player: PlayerServer) {
        with(profileBinding) {
            textViewEmail.text = "Correo: " + player.email.toString()
            textViewName.text = "Nombre: " + player.name.toString()
            Glide.with(profileBinding.root).load(player.urlPicture).into(imageProfile)
        }
    }

    private fun dispatchTakePictureIntent(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager).also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            profileBinding.imageProfile.setImageBitmap(imageBitmap)
        }
    }
}