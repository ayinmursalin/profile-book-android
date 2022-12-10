package com.creativijaya.profilebook.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentContainerBinding
import com.creativijaya.profilebook.util.widget.viewBinding

class MainContainerFragment : Fragment(R.layout.fragment_container) {

    private val binding: FragmentContainerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()
    }

    private fun setupLayout() {
        val navController = (childFragmentManager
            .findFragmentById(R.id.main_fragment_container_view) as NavHostFragment)
            .navController

        binding.mainNavMenu.setupWithNavController(navController)
    }
}
