/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 9/30/20 6:38 AM
 * Last modified 9/30/20 6:12 AM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geeksempire.keepnotes.Utils.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import javax.inject.Inject

object NetworkSettingCallback {
    const val WifiSetting: Int = 111
}

interface InterfaceNetworkCheckpoint {

}

class NetworkCheckpoint @Inject constructor(var context: Context) : InterfaceNetworkCheckpoint {

    fun networkConnection() : Boolean {

        var networkAvailable = false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (connectivityManager != null) {

            val activeNetwork: Network? = connectivityManager.activeNetwork
            val networkCapabilities: NetworkCapabilities? = connectivityManager.getNetworkCapabilities(activeNetwork)

            if (networkCapabilities != null) {

                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {

                        networkAvailable = true

                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {

                        networkAvailable = true

                    }
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {

                        networkAvailable = true

                    }
                }

            }

        }

        return networkAvailable
    }

    fun networkConnectionVpn() : Boolean {

        var networkAvailable = false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        if (connectivityManager != null) {

            val activeNetwork: Network? = connectivityManager.activeNetwork
            val networkCapabilities: NetworkCapabilities? = connectivityManager.getNetworkCapabilities(activeNetwork)

            if (networkCapabilities != null) {

                if (networkConnection()
                    && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {

                    networkAvailable = true

                }

            }

        }

        return networkAvailable
    }

}