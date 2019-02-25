package com.workshop.aroundme.remote.model.response

data class UserRegisterResponse(val id : Int, val fullName: String?, val email : String?, val error : ErrorDescription?)

data class ErrorDescription(val code : Int, val message : String?)