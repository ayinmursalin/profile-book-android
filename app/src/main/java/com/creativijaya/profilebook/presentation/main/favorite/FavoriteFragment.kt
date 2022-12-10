package com.creativijaya.profilebook.presentation.main.favorite

import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentFavoriteBinding
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.widget.viewBinding

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()

    override fun invalidate() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}
