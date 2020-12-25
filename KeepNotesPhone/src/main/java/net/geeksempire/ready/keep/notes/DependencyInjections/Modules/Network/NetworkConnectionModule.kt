/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 6/28/20 2:44 PM
 * Last modified 6/28/20 1:53 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package net.geeksempire.ready.keep.notes.DependencyInjections.Modules.Network

import android.net.ConnectivityManager
import dagger.Binds
import dagger.Module
import net.geeksempire.ready.keep.notes.Utils.Network.NetworkConnectionListener

@Module
abstract class NetworkConnectionModule {

    @Binds
    abstract fun provideNetworkConnectionListener(networkConnectionListener: NetworkConnectionListener/*This is Instance Of Return Type*/): ConnectivityManager.NetworkCallback
}