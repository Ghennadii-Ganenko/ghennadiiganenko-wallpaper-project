package com.ghennadiiganenko.android.wallpaper.fragments.wallpapers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ghennadiiganenko.android.wallpaper.R
import com.ghennadiiganenko.android.wallpaper.databinding.FragmentWallpapersBinding
import com.ghennadiiganenko.android.wallpaper.domain.module.Hit
import com.ghennadiiganenko.android.wallpaper.domain.utils.tagMap
import com.ghennadiiganenko.android.wallpaper.fragments.CommonFragment
import com.ghennadiiganenko.android.wallpaper.fragments.categories.viewmodel.CategoriesViewModel
import com.ghennadiiganenko.android.wallpaper.fragments.wallpapers.adapter.WallpapersAdapter
import org.koin.androidx.navigation.koinNavGraphViewModel
import kotlin.properties.Delegates

class WallpapersFragment : CommonFragment(R.layout.fragment_wallpapers), WallpapersAdapter.ItemClickListener {

    private var binding: FragmentWallpapersBinding by Delegates.notNull()
    private val viewModel by koinNavGraphViewModel<CategoriesViewModel>(R.id.main_navigation_graph)
    private lateinit var wallpapersAdapter: WallpapersAdapter
    private val args: WallpapersFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWallpapersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.inflateIfOnline({inflateRecyclerView(view)}, requireContext(), binding.reloadButton)
    }

    override fun onItemClicked(item: Hit, view: View) {
        val actionToImageFragment =
            WallpapersFragmentDirections.actionWallpapersFragmentToImageFragment(item.largeImageURL)
        navigateTo(actionToImageFragment)
    }

    private fun navigateTo(action: NavDirections) =
        findNavController().navigate(action)

    private fun inflateRecyclerView(view: View) {
        wallpapersAdapter = WallpapersAdapter(this, view)

        binding.wallpapersRecyclerview.adapter = wallpapersAdapter

        viewModel.wallpapersList.observe(viewLifecycleOwner) { result ->
            wallpapersAdapter.submitList(result.tagMap(result.makeAvailableTags())[args.category])
        }
    }
}