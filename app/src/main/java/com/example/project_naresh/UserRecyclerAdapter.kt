package com.example.project_naresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_naresh.databinding.ListItemBinding

class UserRecyclerAdapter(private var userList: ArrayList<File>) : RecyclerView.Adapter<UserRecyclerAdapter.NewHolder>() {

    class NewHolder(private var bind: ListItemBinding) : RecyclerView.ViewHolder(bind.root) {

        fun loadData(file: File){
            bind.apply {
                tvName.text = file.name
                tvSource.text = file.source
                tvMtime.text = file.mtime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewHolder(view)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        val list = userList[position]
        holder.loadData(list)
    }
}