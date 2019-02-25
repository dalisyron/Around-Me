package com.workshop.aroundme.data

import com.workshop.aroundme.data.model.UserEntity
import com.workshop.aroundme.local.datasource.UserLocalDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.remote.datasource.UserRemoteDataSource
import com.workshop.aroundme.remote.model.response.User
import com.workshop.aroundme.remote.service.UserLoginItem
import com.workshop.aroundme.remote.service.UserRegisterItem
import kotlin.concurrent.thread

class UserRepository(private val localDataSource: UserLocalDataSource, private val remoteDataSource: UserRemoteDataSource) {

    fun login(user: UserEntity, userLoginItem : UserLoginItem, rememberFlag : Boolean = false, success : (UserEntity) -> Unit, failure : (String) -> Unit) {
        thread {
            val status = remoteDataSource.getLoginStatus(userLoginItem)
            if (status == UserRemoteDataSource.SUCCESS) {
                //pass
                if (rememberFlag) {
                    localDataSource.login(user)
                }
                success(user)
            } else {
                failure(when (status) {
                    UserRemoteDataSource.INVALID_EMAIL -> "Invalid Email"
                    UserRemoteDataSource.INVALID_EMAIL_OR_PASSWORD -> "Invalid email or password"
                    else -> "Login Error"
                })
            }
        }
    }

    fun register(user: UserEntity, userRegisterItem: UserRegisterItem, success : (UserEntity) -> Unit, failure : (String) -> Unit) {
        thread {
            val status = remoteDataSource.getRegisterStatus(userRegisterItem)
            if (status == UserRemoteDataSource.SUCCESS) {
                //pass
                success(user)
            } else {
                failure(when (status) {
                    UserRemoteDataSource.INVALID_EMAIL -> "Invalid Email"
                    UserRemoteDataSource.DUPLICATE_USER -> "A user with this email already exists."
                    else -> "Register Error"
                })
            }
        }
    }
    fun isLoggedIn() = localDataSource.getUser() != null

}