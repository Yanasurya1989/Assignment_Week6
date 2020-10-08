package com.example.mahasiswa_app_nyanyangsuryana.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mahasiswa_app_nyanyangsuryana.Config.NetworkModule
import com.example.mahasiswa_app_nyanyangsuryana.Model.action.ResponseActoin
import com.example.mahasiswa_app_nyanyangsuryana.Model.getdata.ResponseGetData
import com.example.mahasiswa_app_nyanyangsuryana.Repo.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ViewModelMainActivity : ViewModel() {

    val repository = Repository()

    var responseData = MutableLiveData<ResponseGetData>()
    var responseAction = MutableLiveData<ResponseActoin>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var responseInsert = MutableLiveData<ResponseActoin>()
    var responseEdit = MutableLiveData<ResponseActoin>()

    fun getListData() {

        isLoading.value = true

        repository.getData({
            isLoading.value = false
            responseData.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun deleteListData(id: String) {
        isLoading.value = true

        repository.deleteData(id, {
            isLoading.value = false
            responseAction.value = it
            getListData()
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun updateListData(
        mahasiswaId: String,
        mahasiswaNama: String,
        mahasiswaNohp: String,
        mahasiswaAlamat: String
    ) {

        repository.editData(mahasiswaId, mahasiswaNama, mahasiswaNohp, mahasiswaAlamat, {
           responseEdit.value = it
        }, {
            isError.value = it
        })
    }

    fun insertData(namaMahasiswa: String, nohpMahasiswa: String, alamatMahasiswa: String){
        repository.insertData(namaMahasiswa, nohpMahasiswa, alamatMahasiswa, {
            responseInsert.value = it
        }, {
            isError.value = it
        })
    }
//    fun insertData(mahasiswaNama: String, mahasiswaNohp: String, mahasiswaAlamat: String) {
//        repository.insertData(mahasiswaNama, mahasiswaNohp, mahasiswaAlamat, {
//           responseInsert.value = it
//        },{
//            isError.value = it
//        })
//    }

}