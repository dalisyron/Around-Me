package com.workshop.aroundme.remote
import com.google.gson.Gson
import com.workshop.aroundme.remote.service.UserLoginItem
import okhttp3.HttpUrl
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception


class NetworkManager {

    fun get(url: String): String {
        return URL(url)
            .openStream()
            .bufferedReader()
            .use {
                it.readText()
            }
    }

    fun post(url: String, jsonParameter : String) : String {
        val postData = jsonParameter.toByteArray()
        val urlObject = URL(url)
        val conn = urlObject.openConnection() as HttpURLConnection

        conn.apply {
            doOutput = true
            instanceFollowRedirects = false
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("charset", "utf-8")
            setRequestProperty("Content-Length", postData.size.toString())
            useCaches = false
        }

        DataOutputStream(conn.outputStream).use({ wr -> wr.write(postData) })

        val input = if (conn.responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
            conn.inputStream
        } else {
            conn.errorStream
        }

        val responseStringBuilder = StringBuilder()

        var EOF = false

        while (!EOF) {
            val c = input.read()
            if (c < 0) {
                EOF = true
            } else {
                responseStringBuilder.append(c.toChar())
            }
        }
        return responseStringBuilder.toString()

    }
}
