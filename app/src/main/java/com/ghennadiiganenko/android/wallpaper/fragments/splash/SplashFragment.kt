package com.ghennadiiganenko.android.wallpaper.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ghennadiiganenko.android.wallpaper.R
import com.ghennadiiganenko.android.wallpaper.databinding.FragmentSplashBinding
import kotlin.properties.Delegates

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var binding: FragmentSplashBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemUI(activity?.window)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val actionToCategoriesFragment = SplashFragmentDirections.actionSplashFragmentToCategoriesFragment()

            navigateTo(actionToCategoriesFragment)
        }, 1000)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        showSystemUI(activity?.window)
    }

    private fun hideSystemUI(window: Window?) =
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

    private fun showSystemUI(window: Window?) =
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(window, true)
            WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
        }

    private fun navigateTo(action: NavDirections) =
        findNavController().navigate(action)
}