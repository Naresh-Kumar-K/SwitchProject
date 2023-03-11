package com.example.project_naresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_naresh.databinding.ListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class UserRecyclerAdapter(private var userList: List<File> = mutableListOf()) : RecyclerView.Adapter<UserRecyclerAdapter.NewHolder>() {


     var onClickListener:((File) -> Unit)? = null

    class NewHolder(private var bind: ListItemBinding) : RecyclerView.ViewHolder(bind.root) {

        fun loadData(file: File){
            bind.apply {
                tvName.text = file.name
                tvSource.text = file.source
                tvMtime.text = file.mtime?.let { getDateTime(it) }
            }
        }
        private fun getDateTime(s: String): String? {
            return try {
                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                val netDate = Date(s.toLong() * 1000)
                sdf.format(netDate)
            } catch (e: Exception) {
                e.toString()
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
        holder.itemView.setOnClickListener{ onClickListener?.invoke(list) }
    }

    fun setData(file: List<File>) {
        this.userList = file
        notifyDataSetChanged()
    }

}