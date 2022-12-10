package com.creativijaya.profilebook.presentation.main.favorite

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentFavoriteBinding
import com.creativijaya.profilebook.databinding.ItemPostBinding
import com.creativijaya.profilebook.databinding.ItemPostTagBinding
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.ext.clickWithDebounce
import com.creativijaya.profilebook.util.ext.loadImageUrl
import com.creativijaya.profilebook.util.ext.orFalse
import com.creativijaya.profilebook.util.ext.toGone
import com.creativijaya.profilebook.util.ext.toVisible
import com.creativijaya.profilebook.util.widget.CommonRecyclerViewAdapter
import com.creativijaya.profilebook.util.widget.viewBinding

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    private val binding: FragmentFavoriteBinding by viewBinding()
    private val viewModel: FavoriteViewModel by fragmentViewModel()

    private val postAdapter: CommonRecyclerViewAdapter<PostDto> by lazy {
        CommonRecyclerViewAdapter(
            itemLayoutRes = R.layout.item_post,
            onBind = this::onBindPostItem
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()

        viewModel.getFavoritePosts()

        subscribeToPostList()
    }

    private fun setupLayout() {
        with(binding) {
            rvFavoritePosts.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = postAdapter
            }
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

        btnItemPostLike.apply {
            withState(viewModel) { state ->
                val isFavorite = state.favoritePostAsync.invoke()?.map {
                    it.id
                }?.contains(data.id).orFalse()

                if (isFavorite) {
                    val icon = R.drawable.ic_favorite_active

                    setImageResource(icon)
                    clickWithDebounce {
                        viewModel.removeFavoritePost(data.id)
                    }
                }
            }
        }
    }

    private fun subscribeToPostList() {
        viewModel.onEach(
            FavoriteState::favoritePostAsync,
            uniqueOnly()
        ) { async ->
            when (async) {
                Uninitialized, is Loading -> {
                    binding.shimmerFavoritePosts.apply {
                        toVisible()
                        startShimmer()
                    }
                    binding.rvFavoritePosts.toGone()
                }
                is Success -> {
                    binding.shimmerFavoritePosts.apply {
                        stopShimmer()
                        toGone()
                    }
                    binding.rvFavoritePosts.toVisible()

                    postAdapter.setData(async.invoke())
                }
                is Fail -> {
                    binding.shimmerFavoritePosts.apply {
                        stopShimmer()
                        toGone()
                    }
                    binding.rvFavoritePosts.toVisible()

                    handleError(async.error)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}
