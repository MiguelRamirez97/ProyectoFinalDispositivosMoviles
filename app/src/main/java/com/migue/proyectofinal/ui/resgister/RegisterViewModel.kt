package com.migue.proyectofinal.ui.resgister

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.migue.proyectofinal.server.serverrepository.PlayerServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> =msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    private val playerRepository = PlayerServerRepository()
    val dataValidated: LiveData<Boolean> = dataValidate
    private lateinit var auth: FirebaseAuth
    private val imageFemale = "https://firebasestorage.googleapis.com/v0/b/taptaptap-8eb3e.appspot.com/o/genre%2Ffemale.png?alt=media&token=d1e13473-3eba-4109-9562-fe68b045da6c"
    private val imageMale = "https://firebasestorage.googleapis.com/v0/b/taptaptap-8eb3e.appspot.com/o/genre%2Fmale.png?alt=media&token=9fd54543-01ca-4c2c-aa88-7f4ba34f376d"

    fun validateFields(email: String, namePlayer: String, password: String, repPassword: String, genre: Int) {
        auth = Firebase.auth
        if(email.isNotEmpty() && namePlayer.isNotEmpty() && password.isNotEmpty() && repPassword.isNotEmpty()){
            if (email.trim { it <= ' ' }.matches(emailPattern.toRegex())) {
                if(password == repPassword){
                    if(password.length > 5){
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("Register", "createUserWithEmail:success")
                                    createPlayer(auth.currentUser?.uid, email, namePlayer, genre)
                                    dataValidate.value = true
                                } else {
                                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                                    msg.value = task.exception?.message.toString()
                                }
                            }
                    }else
                        msg.value = "La contraseña debe contener al menos 6 caracteres"
                }else
                    msg.value = "Las contraseñas deben coincidir"
            }else
                msg.value = "Email invalido"
        }else
            msg.value = "Debe llenar todos los campos"
    }

    private fun createPlayer(uid: String?, email: String, namePlayer: String, genre: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            var urlPicture : String = ""
            if(genre == 0){
                urlPicture = imageFemale
            }else if(genre == 1){
                urlPicture = imageMale
            }
            playerRepository.savePlayerInServer(uid, email, namePlayer, urlPicture)
        }
    }
}