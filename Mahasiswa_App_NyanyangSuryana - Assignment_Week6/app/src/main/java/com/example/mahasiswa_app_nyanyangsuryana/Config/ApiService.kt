package com.example.mahasiswa_app_nyanyangsuryana.Config

import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.ResponseGetData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //    getData
    @GET("getData.php")
    fun getData(): Flowable<ResponseGetData>

    //    getDataById
    @GET("getData.php")
    fun getDataById(@Query("id") id : String): Flowable<ResponseGetData>

    //    insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("mahasiswa_nama")nama : String,
                   @Field("mahasiswa_nohp") nohp : String,
                   @Field("mahasiswa_alamat") alamat : String): Flowable<ResponseActoin>

    //    insert
    @FormUrlEncoded
    @POST("update.php")
    fun updatetData(@Field("id_mahasiswa")id: String,
                    @Field("mahasiswa_nama")nama : String,
                    @Field("mahasiswa_nohp") nohp : String,
                    @Field("mahasiswa_alamat") alamat : String): Flowable<ResponseActoin>

    //    insert
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id_mahasiswa") id : String): Flowable<ResponseActoin>


}