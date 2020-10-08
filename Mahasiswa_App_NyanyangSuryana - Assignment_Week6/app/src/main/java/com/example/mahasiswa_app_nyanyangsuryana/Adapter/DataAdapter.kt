package com.example.mahasiswa_app_nyanyangsuryana.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.DataItem
import com.example.mahasiswa_app_nyanyangsuryana.R
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter (val data: List<DataItem>?, val itemClick: OnClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.mahasiswa_nama.text = item?.mahasiswaNama
        holder.mahasiswa_nohp.text = item?.mahasiswaNohp
        holder.mahasiswa_alamat.text = item?.mahasiswaAlamat

        holder.view.setOnClickListener{
            itemClick.detail(item)
        }

        holder.btnHapus.setOnClickListener {
            itemClick.hapus(item)
        }

    }

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view){

        val mahasiswa_nama = view.textNama
        val mahasiswa_nohp = view.textNohp
        val mahasiswa_alamat = view.textAlamat
        val btnHapus = view.btnHapus

    }

    interface OnClickListener{
        fun detail(item: DataItem?)
        fun hapus(item: DataItem?)
    }

}