package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.service.UserLoginItem
import com.workshop.aroundme.remote.service.UserRegisterItem
import com.workshop.aroundme.remote.service.UserService

class UserRemoteDataSource(private val userService : UserService) {

    fun getLoginStatus(userLoginItem : UserLoginItem) : Int {
        val resp = userService.getUserLoginResponse(userLoginItem)
        if (resp.error == null) {
            return SUCCESS
        } else {
            return when (resp.error.message) {
                "Invalid email or password" -> INVALID_EMAIL_OR_PASSWORD
                "Invalid email" -> INVALID_EMAIL
                else -> GENERIC_LOGIN_ERROR
            }
        }
    }

    fun getRegisterStatus(userRegisterItem : UserRegisterItem) : Int {
        val resp = userService.getUserRegisterResponse(userRegisterItem)

        if (resp.error == null) {
            return SUCCESS
        } else {
            return when (resp.error.message) {
                "Invalid email" -> INVALID_EMAIL
                "Key (email)=(${userRegisterItem.email}) already exists." -> DUPLICATE_USER
                else -> GENERIC_REGISTER_ERROR
            }
        }
    }

    companion object {
        const val SUCCESS = 1
        const val INVALID_EMAIL_OR_PASSWORD = 2
        const val INVALID_EMAIL = 3
        const val DUPLICATE_USER = 5
        const val GENERIC_LOGIN_ERROR = 4
        const val GENERIC_REGISTER_ERROR = 6
    }
}