package com.techies.codinghub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techies.codinghub.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val login = sharedPref.getBoolean("login", false)

        if(login)
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_sign_in.setOnClickListener {
            if(edit_email.text.toString() != "kooo@gmail.com")
            {
                input_layout_email.error = "Invalid"
                edit_email.requestFocus()
            }
            else if(edit_password.text.toString() != "k000")
            {
                input_layout_password.error = "Invalid"
                edit_password.requestFocus()
            }
            else {
                val prefEditor: SharedPreferences.Editor = sharedPref.edit()
                prefEditor.putBoolean("login", true)
                prefEditor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btn_sign_up.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
