package com.ghennadiiganenko.android.wallpaper.fragments.image

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.ghennadiiganenko.android.wallpaper.R
import com.ghennadiiganenko.android.wallpaper.databinding.FragmentImageBinding
import com.ghennadiiganenko.android.wallpaper.fragments.CommonFragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import java.io.IOException
import kotlin.properties.Delegates


class ImageFragment : CommonFragment(R.layout.fragment_image) {

    private var binding: FragmentImageBinding by Delegates.notNull()
    private val args: ImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.inflateIfOnline({inflateRecyclerView()}, requireContext(), binding.reloadButton)
    }

    private fun inflateRecyclerView() {
        val target = object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                Log.e("my log", "OnBitmapLoaded")
                val wallpaperManager = WallpaperManager.getInstance(requireContext())
                Toast.makeText(requireContext(), "Wallpaper installed", Toast.LENGTH_SHORT).show()
                try {
                    wallpaperManager.setBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e("my log", "IOException->" + e.message)
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Log.e("my log", "" + e?.message)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                Log.e("my log", "OnPrepareLoad");
            }
        }

        binding.apply {
            Picasso
                .get()
                .load(root.context.resources.getString(R.string.image_url, args.imageUrl))
                .into(wallpaperImage)

            installButton.setOnClickListener {
                Picasso
                    .get()
                    .load(root.context.resources.getString(R.string.image_url, args.imageUrl))
                    .into(target)
            }
        }
    }
}