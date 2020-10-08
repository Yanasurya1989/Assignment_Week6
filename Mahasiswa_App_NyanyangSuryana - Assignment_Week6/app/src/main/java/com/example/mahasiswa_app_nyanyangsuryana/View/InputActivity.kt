package com.example.mahasiswa_app_nyanyangsuryana.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mahasiswa_app_nyanyangsuryana.Config.NetworkModule
import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.DataItem
import com.example.mahasiswa_app_nyanyangsuryana.R
import com.example.mahasiswa_app_nyanyangsuryana.ViewModel.ViewModelMainActivity
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        viewModel = ViewModelProviders.of(this).get(ViewModelMainActivity::class.java)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            etNama.setText(getData.mahasiswaNama)
            etNohp.setText(getData.mahasiswaNohp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btn1.text = "Update"
        }

        when (btn1.text) {
            "Update" -> {

                btn1.setOnClickListener {
                    getData?.idMahasiswa?.let { it ->
                        viewModel.updateListData(
                            it,
                            etNama.text.toString(),
                            etNohp.text.toString(),
                            etAlamat.text.toString()
                        )
                    }
                }
            }
            else -> {
                btn1.setOnClickListener {
                    viewModel.insertData(
                        etNama.text.toString(),
                        etNohp.text.toString(),
                        etAlamat.text.toString()
                    )
                }
            }
        }
        btn2.setOnClickListener {
            finish()
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.responseInsert.observe(this, Observer { insertData(it) })
        viewModel.responseEdit.observe(this, Observer { editData(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
    }

    private fun showError(it: Throwable?) {
        showToast(it?.message.toString())
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun editData(it: ResponseActoin?) {
        showToast("Data sudah diganti")
        finish()
    }

    private fun insertData(it: ResponseActoin?) {
        showToast("Data tersimpan")
        finish()
    }
//    private fun inputData(mahasiswa_nama: String?, mahasiswa_nohp: String?, mahasiswa_alamat: String?){
//        val input = NetworkModule.service().insertData(mahasiswa_nama ?: "", mahasiswa_nohp ?: "", mahasiswa_alamat ?: "")
//        input.enqueue(object : Callback<ResponseActoin>{
//
//            override fun onResponse(
//                call: Call<ResponseActoin>,
//                response: Response<ResponseActoin>
//            ) {
//                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
//                finish()
//
//            }
//
//            override fun onFailure(call: Call<ResponseActoin>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

//    private fun updateData(id: String?, mahasiswa_nama: String?, mahasiswa_nohp: String?, mahasiswa_alamat: String?){
//        val input = NetworkModule.service().updatetData( id ?: "", mahasiswa_nama ?: "", mahasiswa_nohp ?: "", mahasiswa_alamat ?: "")
//        input.enqueue(object : Callback<ResponseActoin>{
//
//            override fun onResponse(
//                call: Call<ResponseActoin>,
//                response: Response<ResponseActoin>
//            ) {
//                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
//                finish()
//
//            }
//
//            override fun onFailure(call: Call<ResponseActoin>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }
}