package com.migue.proyectofinal.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.migue.proyectofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }



    /*private lateinit var mainBinding: ActivityMainBinding

    private var emailReceivedMain : String? = ""
    private var passwordReceivedMain : String? = ""
    private var namePlayerReceivedMain : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        emailReceivedMain = intent.extras?.getString("email")
        passwordReceivedMain = intent.extras?.getString("password")
        namePlayerReceivedMain = intent.extras?.getString("name")

        with(mainBinding) {
            namePlayerTextView.text = getString(R.string.info, namePlayerReceivedMain)
            emailTextView.text = emailReceivedMain
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sing_out -> goToLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email", emailReceivedMain)
        intent.putExtra("password", passwordReceivedMain)
        intent.putExtra("name", namePlayerReceivedMain)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }*/
}