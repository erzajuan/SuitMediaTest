package com.bastilla.suitmediaapp.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.bastilla.suitmediaapp.secondscreen.SecondScreenActivity
import com.bastilla.suitmediaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.edtName.text

        binding.btnCheck.setOnClickListener {
            if (isPalindrome(name)) {
                binding.edtStatus.setText("isPalindrome")
            } else {
                binding.edtStatus.setText("not palindrome")
            }
        }
        binding.btnNext.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondScreenActivity::class.java)
            intent.putExtra(SecondScreenActivity.FIRSTNAME, name.toString())
            startActivity(intent)
        }
    }

    private fun isPalindrome(name: Editable): Boolean {
        val stringBuilder = StringBuilder(name.toString())
        val reverseName = stringBuilder.reverse().toString()

        return name.toString().replace("\\s".toRegex(), "")
            .equals(reverseName.replace("\\s".toRegex(), ""), ignoreCase = true)
    }


}