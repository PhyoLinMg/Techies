package com.techies.codinghub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.techies.codinghub.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val sharedPref: SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val login = sharedPref.getBoolean("login", false)

        if(login)
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_create_account.setOnClickListener {
            val prefEditor: SharedPreferences.Editor = sharedPref.edit()
            prefEditor.putBoolean("login", true)
            prefEditor.apply()

            Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
        {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}
