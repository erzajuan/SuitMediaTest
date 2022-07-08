package com.bastilla.suitmediaapp.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bastilla.suitmediaapp.databinding.ActivitySecondScreenBinding
import com.bastilla.suitmediaapp.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val firstName = intent.getStringExtra(FIRSTNAME).toString()
        binding.tvFirstName.text = firstName

        val selectedName = intent.getStringExtra(SELECTEDNAME).toString()
        binding.tvSelectedName.text = selectedName


        binding.btnChoose.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            intent.putExtra(ThirdScreenActivity.FIRSTNAME, firstName)
            startActivity(intent)
        }
    }

    companion object {
        const val FIRSTNAME = "first_name"
        const val SELECTEDNAME = "selected_name"
    }
}