package com.example.mahasiswa_app_nyanyangsuryana.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mahasiswa_app_nyanyangsuryana.Adapter.DataAdapter
import com.example.mahasiswa_app_nyanyangsuryana.Config.NetworkModule
import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.DataItem
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.ResponseGetData
import com.example.mahasiswa_app_nyanyangsuryana.R
import com.example.mahasiswa_app_nyanyangsuryana.ViewModel.ViewModelMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModelMainActivity::class.java)

        fab.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        viewModel.getListData()

//        viewModel.inputData()

        attachObserve()

//        showData(it)

    }

    private fun attachObserve() {
        viewModel.responseData.observe(this, Observer { showData(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
        viewModel.responseAction.observe(this, Observer { hapusData(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
    }

    private fun hapusData(it: ResponseActoin?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progress_id.visibility = View.VISIBLE
        } else {
            progress_id.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showData(it: ResponseGetData) {
//        val listAnggota = NetworkModule.service().getData()
//        listAnggota.enqueue(object : Callback<ResponseGetData>{
//
//            override fun onResponse(
//                call: Call<ResponseGetData>,
//                response: Response<ResponseGetData>
//            ) {
//                if (response.isSuccessful){
//
//                    val item = response.body()?.data

        val adapter = DataAdapter(it.data, object : DataAdapter.OnClickListener {
            override fun detail(item: DataItem?) {

                val intent = Intent(applicationContext, InputActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)

            }

            override fun hapus(item: DataItem?) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Hapus Data")
                    setMessage("anda yakin akan menghapus?")
                    setPositiveButton("Delete") { dialogInterface, i ->

                        viewModel.deleteListData(item?.idMahasiswa ?: "")
                        dialogInterface.dismiss()
                    }
                    setNegativeButton("Cancel") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
//                hapusData(item?.idMahasiswa)
            }

        })

        list.adapter = adapter
    }

//            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//            }
//
//        })

//private fun hapusData(idMahasiswa: String?) {
//    val hapus = NetworkModule.service().deleteData(idMahasiswa ?: "")
//    hapus.enqueue(object : Callback<ResponseActoin> {
//        override fun onResponse(
//            call: Call<ResponseActoin>,
//            response: Response<ResponseActoin>
//        ) {
//            Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT)
//                .show()
//            showData()
//        }
//
//        override fun onFailure(call: Call<ResponseActoin>, t: Throwable) {
//            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//        }
//    })
//}

    override fun onResume() {
        super.onResume()
        viewModel.getListData()
    }
}