package com.creativijaya.profilebook.presentation.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.creativijaya.profilebook.databinding.ActivityBaseNoToolbarBinding

abstract class BaseNoToolbarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseNoToolbarBinding
    private var navHostController: NavController? = null

    @NavigationRes
    abstract fun getNavHost(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseNoToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.baseFragmentContainer.id
        ) as NavHostFragment

        navHostController = navHostFragment.findNavController()
        navHostController?.setGraph(getNavHost(), intent.extras)
    }

}
