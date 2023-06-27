package com.project.habithero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.habithero.R
import com.project.habithero.Task
import com.project.habithero.User

class HomeAdapter(private val taskList: ArrayList<Task>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_task,
        parent, false)

        return ViewHolder(itemViewHolder)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val task : Task = taskList[position]
        holder.NamaKegiatan.text = task.nama_kegiatan
        holder.FrekuensiKegiatan.text = task.frekuensi
        holder.TanggalKegiatan.text = task.tanggal

    }

    public class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val NamaKegiatan : TextView = itemView.findViewById(R.id.sNamaKegiatan)
        val FrekuensiKegiatan : TextView = itemView.findViewById(R.id.sFrekuensiKegiatan)
        val TanggalKegiatan : TextView = itemView.findViewById(R.id.sTanggalKegiatan)
    }

}