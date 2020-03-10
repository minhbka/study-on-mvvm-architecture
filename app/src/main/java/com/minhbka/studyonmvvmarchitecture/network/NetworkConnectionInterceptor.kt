package com.minhbka.studyonmvvmarchitecture.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.minhbka.studyonmvvmarchitecture.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
):Interceptor {
    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable()){
            throw NoInternetException("Make sure you have an active data connection")
        }

        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable():Boolean{
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

//        var result = false
//        connectivityManager?.let {
//            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
//                result = when{
//                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                    else -> false
//                }
//            }
//        }
//        return result
//
        connectivityManager?.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}