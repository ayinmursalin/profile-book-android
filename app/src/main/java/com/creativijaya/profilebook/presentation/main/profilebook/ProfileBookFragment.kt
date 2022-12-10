package com.creativijaya.profilebook.presentation.main.profilebook

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentProfileBookBinding
import com.creativijaya.profilebook.databinding.ItemProfileBookBinding
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.ext.loadImageUrl
import com.creativijaya.profilebook.util.ext.toGone
import com.creativijaya.profilebook.util.ext.toVisible
import com.creativijaya.profilebook.util.widget.CommonRecyclerViewAdapter
import com.creativijaya.profilebook.util.widget.viewBinding

class ProfileBookFragment : BaseFragment(R.layout.fragment_profile_book) {

    private val binding: FragmentProfileBookBinding by viewBinding()
    private val viewModel: ProfileBookViewModel by fragmentViewModel()

    private val profileAdapter: CommonRecyclerViewAdapter<ProfileDto> by lazy {
        CommonRecyclerViewAdapter(
            itemLayoutRes = R.layout.item_profile_book,
            onBind = this::onBindProfileItem
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()

        subscribeToProfileBooks()
    }

    private fun setupLayout() {
        with(binding) {
            rvProfileBooks.adapter = profileAdapter
        }
    }

    override fun invalidate() {

    }

    private fun onBindProfileItem(
        data: ProfileDto,
        view: View
    ) = ItemProfileBookBinding.bind(view).apply {
        ivItemProfileBookPicture.loadImageUrl(data.picture)
        tvItemProfileBookName.text = getString(
            R.string.format_full_name,
            data.title.replaceFirstChar { it.uppercase() },
            data.firstName,
            data.lastName
        )
    }

    private fun subscribeToProfileBooks() {
        viewModel.onEach(
            ProfileBookState::profileBookAsync,
            uniqueOnly()
        ) { async ->
            when (async) {
                Uninitialized, is Loading -> {
                    binding.rvProfileBooks.toGone()
                    binding.shimmerProfileBooks.apply {
                        toVisible()
                        startShimmer()
                    }
                }
                is Success -> {
                    binding.shimmerProfileBooks.apply {
                        stopShimmer()
                        toGone()
                    }
                    binding.rvProfileBooks.toVisible()

                    profileAdapter.setData(async.invoke().data)
                }
                is Fail -> {
                    binding.shimmerProfileBooks.apply {
                        stopShimmer()
                        toGone()
                    }

                    handleError(async.error)
                }
            }
        }
    }

}
