package com.workshop.aroundme.remote.service

import com.google.gson.Gson
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.model.response.User
import com.workshop.aroundme.remote.model.response.UserLoginResponse
import com.workshop.aroundme.remote.model.response.UserRegisterResponse
import java.lang.Exception

data class UserRegisterItem(val fullName : String, val email : String, val password : String)
data class UserLoginItem(val email : String, val password: String)

class UserService(private val networkManager: NetworkManager) {

    fun getUserLoginResponse(userLoginItem : UserLoginItem) : UserLoginResponse {
        val jsonLoginParameter = Gson().toJson(userLoginItem)
        val jsonResponseString = networkManager.post("https://restapis.xyz/around-me/v1/user/login", jsonLoginParameter)

        val response = Gson().fromJson(jsonResponseString, UserLoginResponse::class.java)

        return response
    }

    fun getUserRegisterResponse(userRegisterItem: UserRegisterItem) : UserRegisterResponse {
        val jsonRegisterParameter = Gson().toJson(userRegisterItem)
        val jsonResponseString = networkManager.post("https://restapis.xyz/around-me/v1/user/register", jsonRegisterParameter)

        val response = Gson().fromJson(jsonResponseString, UserRegisterResponse::class.java)

        return response
    }
}
