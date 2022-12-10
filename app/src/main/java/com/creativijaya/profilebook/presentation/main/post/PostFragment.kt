package com.creativijaya.profilebook.presentation.main.post

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentPostBinding
import com.creativijaya.profilebook.databinding.ItemPostBinding
import com.creativijaya.profilebook.databinding.ItemPostLoadingBinding
import com.creativijaya.profilebook.databinding.ItemPostTagBinding
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.ext.clickWithDebounce
import com.creativijaya.profilebook.util.ext.loadImageUrl
import com.creativijaya.profilebook.util.ext.toGone
import com.creativijaya.profilebook.util.ext.toVisible
import com.creativijaya.profilebook.util.widget.CommonEndlessScrollListener
import com.creativijaya.profilebook.util.widget.CommonRecyclerViewAdapter
import com.creativijaya.profilebook.util.widget.viewBinding

class PostFragment : BaseFragment(R.layout.fragment_post) {

    private val binding: FragmentPostBinding by viewBinding()
    private val viewModel: PostViewModel by fragmentViewModel()

    private val postAdapter: CommonRecyclerViewAdapter<PostDto> by lazy {
        CommonRecyclerViewAdapter(
            itemLayoutRes = R.layout.item_post,
            loadingLayoutRes = R.layout.item_post_loading,
            onBind = this::onBindPostItem,
            onBindLoading = this::onBindLoadingItem,
        )
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }

    private val scrollListener: CommonEndlessScrollListener by lazy {
        object : CommonEndlessScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.getPost(page = page)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()

        viewModel.getPost(FIRST_PAGE)

        subscribeToPostList()
    }

    private fun setupLayout() {
        with(binding) {
            rvPosts.apply {
                layoutManager = linearLayoutManager
                adapter = postAdapter
                addOnScrollListener(scrollListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.rvPosts.apply {
            layoutManager = null
            clearOnScrollListeners()
        }
    }


    override fun invalidate() {

    }

    private fun onBindPostItem(
        data: PostDto,
        view: View
    ) = ItemPostBinding.bind(view).apply {
        ivItemPostUserPicture.loadImageUrl(data.owner.picture)
        ivItemPostBackdrop.loadImageUrl(data.image)

        tvItemPostUserName.text = getString(
            R.string.format_full_name,
            data.owner.title.replaceFirstChar { it.uppercase() },
            data.owner.firstName,
            data.owner.lastName
        )
        tvItemPostPostDate.text = data.publishDate
        tvItemPostDescription.text = data.text
        tvItemPostLikeCount.text = getString(
            R.string.format_likes,
            data.likes
        )

        rvItemPostTags.adapter = CommonRecyclerViewAdapter<String>(
            itemLayoutRes = R.layout.item_post_tag,
            onBind = { tag, view ->
                ItemPostTagBinding.bind(view).apply {
                    root.text = tag
                }
            }
        ).apply {
            setData(data.tags)
        }

        btnItemPostLike.clickWithDebounce {
            viewModel.addFavoritePost(data)
        }
    }

    private fun onBindLoadingItem(view: View) = ItemPostLoadingBinding.bind(view).apply {
        root.startShimmer()
    }

    private fun subscribeToPostList() {
        viewModel.onEach(
            PostState::postAsync,
            PostState::currentPage,
            uniqueOnly()
        ) { async, currentPage ->
            when (async) {
                Uninitialized, is Loading -> {
                    if (currentPage == 1) {
                        binding.shimmerPosts.apply {
                            toVisible()
                            startShimmer()
                        }
                        binding.rvPosts.toGone()
                    } else {
                        postAdapter.showLoading()
                        scrollListener.showLoading()
                    }
                }
                is Success -> {
                    if (currentPage == 1) {
                        binding.shimmerPosts.apply {
                            stopShimmer()
                            toGone()
                        }
                        binding.rvPosts.toVisible()

                        postAdapter.setData(async.invoke().data)
                    } else {
                        postAdapter.hideLoading()
                        scrollListener.hideLoading()

                        postAdapter.addData(async.invoke().data)
                    }
                }
                is Fail -> {
                    if (currentPage == 1) {
                        binding.shimmerPosts.apply {
                            stopShimmer()
                            toGone()
                        }
                        binding.rvPosts.toVisible()
                    } else {
                        postAdapter.hideLoading()
                        scrollListener.hideLoading()
                    }

                    handleError(async.error)
                }
            }
        }
    }

    companion object {
        private const val FIRST_PAGE = 1

        @JvmStatic
        fun newInstance() = PostFragment()
    }
}
