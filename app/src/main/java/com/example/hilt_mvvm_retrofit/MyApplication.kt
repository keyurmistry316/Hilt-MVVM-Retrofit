package com.example.hilt_mvvm_retrofit

import android.app.Application
import android.content.Intent

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.Uri
import android.provider.Settings
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MyApplication: Application(){

    private lateinit var connectivityManager: ConnectivityManager
    @Inject
    lateinit var networkRequest:NetworkRequest

    companion object{
        var isNetworkAvailable = false
    }

    override fun onCreate() {
        super.onCreate()

        try {
            if (checkSystemWritePermission()) {
                connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
                connectivityManager.requestNetwork(networkRequest, networkCallback)
            } else {
               openAndroidPermissionsMenu()
            }
        } catch (e: Exception) {

        }

    }

    private fun checkSystemWritePermission(): Boolean {
        return (Settings.System.canWrite(applicationContext))
    }

    private fun openAndroidPermissionsMenu() {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.setData(Uri.parse("package:" + applicationContext.packageName))
        applicationContext.startActivity(intent)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isNetworkAvailable = true
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            isNetworkAvailable = false
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}