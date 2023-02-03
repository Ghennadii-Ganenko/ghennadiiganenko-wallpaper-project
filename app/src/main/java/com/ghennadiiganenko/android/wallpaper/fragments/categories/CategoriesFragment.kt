package com.ghennadiiganenko.android.wallpaper.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ghennadiiganenko.android.wallpaper.R
import com.ghennadiiganenko.android.wallpaper.databinding.FragmentCategoriesBinding
import com.ghennadiiganenko.android.wallpaper.fragments.CommonFragment
import com.ghennadiiganenko.android.wallpaper.fragments.categories.adapter.CategoriesAdapter
import com.ghennadiiganenko.android.wallpaper.fragments.categories.viewmodel.CategoriesViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel
import kotlin.properties.Delegates

class CategoriesFragment : CommonFragment(R.layout.fragment_categories), CategoriesAdapter.ItemClickListener {

    private var binding: FragmentCategoriesBinding by Delegates.notNull()
    private val viewModel by koinNavGraphViewModel<CategoriesViewModel>(R.id.main_navigation_graph)
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.inflateIfOnline({inflateRecyclerView(view)}, requireContext(), binding.reloadButton)
    }

    override fun onItemClicked(item: String, view: View) {
        val actionToWallpapersFragment = CategoriesFragmentDirections.actionCategoriesToWallpaper(item)

        navigateTo(actionToWallpapersFragment)
    }

    private fun navigateTo(action: NavDirections) =
        findNavController().navigate(action)

    private fun inflateRecyclerView(view: View) {
        categoriesAdapter = CategoriesAdapter(this, view)

        binding.categoriesRecyclerview.adapter = categoriesAdapter


        viewModel.wallpapersList.observe(viewLifecycleOwner) { result ->

            categoriesAdapter.submitList(result.makeAvailableTags())
        }

        viewModel.getWallpapersList()
    }
}