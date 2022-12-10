package com.creativijaya.profilebook.presentation.main.profilebook

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentProfileBookBinding
import com.creativijaya.profilebook.databinding.ItemProfileBookBinding
import com.creativijaya.profilebook.databinding.ItemProfileBookLoadingBinding
import com.creativijaya.profilebook.domain.models.user.ProfileDto
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.ext.loadImageUrl
import com.creativijaya.profilebook.util.ext.toGone
import com.creativijaya.profilebook.util.ext.toVisible
import com.creativijaya.profilebook.util.widget.CommonEndlessScrollListener
import com.creativijaya.profilebook.util.widget.CommonRecyclerViewAdapter
import com.creativijaya.profilebook.util.widget.viewBinding

class ProfileBookFragment : BaseFragment(R.layout.fragment_profile_book) {

    private val binding: FragmentProfileBookBinding by viewBinding()
    private val viewModel: ProfileBookViewModel by fragmentViewModel()

    private val gridLayoutManager: GridLayoutManager by lazy {
        GridLayoutManager(requireContext(), SPAN_COUNT)
    }

    private val scrollListener: CommonEndlessScrollListener by lazy {
        object : CommonEndlessScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.getProfileBook(page = page)
            }
        }
    }

    private val profileAdapter: CommonRecyclerViewAdapter<ProfileDto> by lazy {
        CommonRecyclerViewAdapter(
            itemLayoutRes = R.layout.item_profile_book,
            loadingLayoutRes = R.layout.item_profile_book_loading,
            onBind = this::onBindProfileItem,
            onBindLoading = this::onBindLoadingItem
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()

        viewModel.getProfileBook(FIRST_PAGE)

        subscribeToProfileBooks()
    }

    private fun setupLayout() {
        with(binding) {
            scrollListener.resetState()

            rvProfileBooks.apply {
                layoutManager = gridLayoutManager
                adapter = profileAdapter
                addOnScrollListener(scrollListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.rvProfileBooks.apply {
            layoutManager = null
            clearOnScrollListeners()
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

    private fun onBindLoadingItem(
        view: View
    ) = ItemProfileBookLoadingBinding.bind(view).apply {
        root.startShimmer()
    }

    private fun subscribeToProfileBooks() {
        viewModel.onEach(
            ProfileBookState::profileBookAsync,
            ProfileBookState::currentPage,
            uniqueOnly()
        ) { async, currentPage ->
            when (async) {
                Uninitialized, is Loading -> {
                    if (currentPage == 1) {
                        binding.rvProfileBooks.toGone()
                        binding.shimmerProfileBooks.apply {
                            toVisible()
                            startShimmer()
                        }
                    } else {
                        profileAdapter.showLoading()
                        scrollListener.showLoading()
                    }
                }
                is Success -> {
                    if (currentPage == 1) {
                        binding.shimmerProfileBooks.apply {
                            stopShimmer()
                            toGone()
                        }
                        binding.rvProfileBooks.toVisible()

                        profileAdapter.setData(async.invoke().data)
                    } else {
                        profileAdapter.hideLoading()
                        scrollListener.hideLoading()

                        profileAdapter.addData(async.invoke().data)
                    }
                }
                is Fail -> {
                    if (currentPage == 1) {
                        binding.shimmerProfileBooks.apply {
                            stopShimmer()
                            toGone()
                        }
                        binding.rvProfileBooks.toVisible()
                    } else {
                        profileAdapter.hideLoading()
                        scrollListener.hideLoading()
                    }

                    handleError(async.error)
                }
            }
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val SPAN_COUNT = 2
    }

}
