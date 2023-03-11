package com.example.project_naresh

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.project_naresh.databinding.ActivityUpdateDeleteBinding

class UpdateDeleteActivity : AppCompatActivity() {

    lateinit var viewModel: UpdateDeleteViewModel
    private var std: File? = null
    lateinit var binding: ActivityUpdateDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UpdateDeleteViewModel::class.java]
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update_delete)
        std = intent.getParcelableExtra(EXTRA_DATA)

        binding.apply {
            etName.setText(std?.name.toString())
            etOriginal.setText(std?.original.toString())
            etRotation.setText(std?.rotation.toString())
            etSource.setText(std?.source.toString())

            tvBtih.text = std?.btih.toString()
            tvCrc32.text = std?.crc32.toString()
            tvFormat.text = std?.format.toString()
            tvMd5.text = std?.md5.toString()
            tvMtime.text = std?.mtime.toString()
            tvSize.text = std?.size.toString()
            tvSummation.text = std?.summation.toString()
            bUpdate.setOnClickListener {
                updateData()
                Toast.makeText(this@UpdateDeleteActivity,"Updated Successfully!!",Toast.LENGTH_SHORT).show()
            }
            bDelete.setOnClickListener {
                deleteData()
                finish()
                Toast.makeText(this@UpdateDeleteActivity,"Deleted Successfully!!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateData(){
        binding.apply {
           std?.name = etName.text.toString()
            std?.original = etOriginal.text.toString()
            std?.rotation = etRotation.text.toString()
            std?.source = etSource.text.toString()
            std?.mtime = (System.currentTimeMillis() / 1000).toString()
        }
        std?.let { viewModel.updateFile(it) }
    }

    private fun deleteData(){
        std?.let { viewModel.deleteFile(it) }
    }
}