package com.techies.codinghub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.techies.codinghub.ui.activity.CourseActivity
import com.techies.codinghub.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_purchase.*

class PurchaseActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        btn_purchase.setOnClickListener {
            Toast.makeText(this, "Purchased", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }


    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_ooredoo ->
                    if (checked) {
                        // Pirates are the best

                    }
                R.id.radio_visa ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }

    override fun onBackPressed() {

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
