package com.example.mangary3.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.example.mangary3.domain.repository.NetworkRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import timber.log.Timber

class NetworkRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): NetworkRepository {
    override val networkStatus: Flow<Boolean> = callbackFlow {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Timber.d("Network currently available")
                trySend(true)
            }

            override fun onLost(network: Network) {
                Timber.d("No Networks available")
                trySend(false)
            }

        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }

    }.distinctUntilChanged()
}