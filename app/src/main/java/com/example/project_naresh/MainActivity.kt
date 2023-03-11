package com.example.project_naresh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_naresh.databinding.ActivityMainBinding

const val BASE_URL = "https://archive.org/metadata/"
const val EXTRA_DATA = "data"

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.fileList.observe(this) { list ->
            binding.rvOne.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvOne.setHasFixedSize(true)
            adapter = UserRecyclerAdapter(list)
            binding.rvOne.adapter = adapter
            adapter.onClickListener = { file ->
                val intent = Intent(this, UpdateDeleteActivity::class.java)
                intent.putExtra(EXTRA_DATA, file)
                startActivity(intent)
            }
        }
    }


}