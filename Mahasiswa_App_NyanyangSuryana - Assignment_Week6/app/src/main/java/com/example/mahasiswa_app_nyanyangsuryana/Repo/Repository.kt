package com.example.mahasiswa_app_nyanyangsuryana.Repo

import com.example.mahasiswa_app_nyanyangsuryana.Config.NetworkModule
import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.ResponseGetData
import com.google.gson.annotations.Until
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository {

    fun getData(
        responHandler: (ResponseGetData) -> Unit, errorHandler: (Throwable) -> Unit
    ){
        NetworkModule.service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            },{
                errorHandler(it)
            })
    }

    fun insertData(
        mahasiswaNama: String, mahasiswaNohp: String, mahasiswaAlamat: String,
        responHandler: (ResponseActoin) -> Unit, errorHandler: (Throwable) -> Unit
    ){
        if (mahasiswaNama.isNotEmpty() && mahasiswaNohp.isNotEmpty() && mahasiswaAlamat.isNotEmpty()) {
            NetworkModule.service().insertData(mahasiswaNama, mahasiswaNohp, mahasiswaAlamat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responHandler(it)
                }, {
                    errorHandler(it)
                })
        }else {
            errorHandler
        }
    }

    fun deleteData(
        id: String,
        responHandler: (ResponseActoin) -> Unit, errorHandler: (Throwable) -> Unit
    ){
        NetworkModule.service().deleteData(id?:"").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun editData(
        mahasiswaId: String, mahasiswaNama: String, mahasiswaNohp: String, mahasiswaAlamat: String,
        responHandler: (ResponseActoin) -> Unit, errorHandler: (Throwable) -> Unit
    ){
        if (mahasiswaNama.isNotEmpty() && mahasiswaNohp.isNotEmpty() && mahasiswaAlamat.isNotEmpty()) {
            NetworkModule.service().updatetData(mahasiswaId, mahasiswaNama, mahasiswaNohp, mahasiswaAlamat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responHandler(it)
                }, {
                    errorHandler(it)
                })
        } else {
            errorHandler
        }
    }

}