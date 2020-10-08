package com.example.mahasiswa_app_nyanyangsuryana.Model.action

import com.google.gson.annotations.SerializedName

data class ResponseActoin(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
