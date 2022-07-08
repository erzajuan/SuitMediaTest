package com.bastilla.suitmediaapp.thirdscreen

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bastilla.suitmediaapp.secondscreen.SecondScreenActivity
import com.bastilla.suitmediaapp.api.DataItem
import com.bastilla.suitmediaapp.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel by viewModels<ThirdScreenViewModel>()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.listUser.observe(this) {
            setUserData(it)
        }
    }

    private fun setUserData(listGithubUser: List<DataItem>) {
        val listUser = ArrayList<DataItem>()
        for (user in listGithubUser) {
            listUser.clear()
            listUser.addAll(listGithubUser)
        }

        var layoutManager = LinearLayoutManager(this)
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = GridLayoutManager(this, 2)
        }
        binding.rvUser.layoutManager = layoutManager
        binding.rvUser.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        adapter = UserAdapter(listUser)
        binding.rvUser.adapter = adapter

        val firstName = intent.getStringExtra(FIRSTNAME).toString()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java).also {
                    it.putExtra(
                        SecondScreenActivity.SELECTEDNAME,
                        data.firstName + " " + data.lastName
                    )
                    it.putExtra(SecondScreenActivity.FIRSTNAME, firstName)
                    startActivity(it)
                }
            }
        })
    }

    companion object {
        const val FIRSTNAME = "first_name"
    }

}