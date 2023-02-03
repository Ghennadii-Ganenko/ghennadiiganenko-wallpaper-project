package com.ghennadiiganenko.android.wallpaper.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

open class CommonFragment(layout: Int) : Fragment(layout) {

    protected fun View.inflateIfOnline(
        inflateRecyclerView: (View) -> Unit,
        context: Context,
        button: Button
    ) {

        if (isDeviceOnline(context)) {
            inflateRecyclerView(this)
        }
        else {
            showReloadButton(
                { inflateRecyclerView(this) },
                context,
                button
            )
            showCheckConnectionMessage(context)
        }
    }

    protected fun isDeviceOnline(context: Context): Boolean {
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
        return if (networkCapabilities == null) {
            Log.d("my log", "Device Offline")
            false
        } else {
            Log.d("my log", "Device Online")
            true
        }
    }

    private fun showReloadButton(
        listener: () -> Unit,
        context: Context,
        button: Button) {

        button.visibility = View.VISIBLE
        button.setOnClickListener {
            if (isDeviceOnline(context)) {
                listener.invoke()
                button.visibility = View.GONE
            } else {
                showCheckConnectionMessage(context)
            }
        }
    }

    private fun showCheckConnectionMessage(context: Context) =
        Toast.makeText(context, "Please, check connection", Toast.LENGTH_LONG).show()

}