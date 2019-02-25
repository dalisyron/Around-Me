package com.workshop.aroundme.remote.model.response

data class UserLoginResponse(val id : Int, val fullName: String?, val email: String, val error : ErrorDescription?)