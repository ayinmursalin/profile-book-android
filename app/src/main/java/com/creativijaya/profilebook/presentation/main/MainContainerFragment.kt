package com.creativijaya.profilebook.presentation.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentContainerBinding
import com.creativijaya.profilebook.presentation.main.favorite.FavoriteFragment
import com.creativijaya.profilebook.presentation.main.post.PostFragment
import com.creativijaya.profilebook.presentation.main.profilebook.ProfileBookFragment
import com.creativijaya.profilebook.util.widget.viewBinding
import com.google.android.material.navigation.NavigationBarView

class MainContainerFragment : Fragment(R.layout.fragment_container),
    NavigationBarView.OnItemSelectedListener {

    private val binding: FragmentContainerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()
    }

    private fun setupLayout() {
        with(binding) {
            navigateToProfileBookPage()
            mainNavMenu.setOnItemSelectedListener(this@MainContainerFragment)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ProfileBookFragment -> navigateToProfileBookPage()
            R.id.PostFragment -> navigateToPostPage()
            R.id.FavoriteFragment -> navigateToFavoritePage()
        }
        return true
    }

    private fun navigateToProfileBookPage() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.main_fragment_container_view,
                ProfileBookFragment.newInstance()
            )
            .commitAllowingStateLoss()
    }

    private fun navigateToPostPage() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.main_fragment_container_view,
                PostFragment.newInstance()
            )
            .commitAllowingStateLoss()
    }

    private fun navigateToFavoritePage() {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.main_fragment_container_view,
                FavoriteFragment.newInstance()
            )
            .commitAllowingStateLoss()
    }
}
