package com.creativijaya.profilebook.presentation.main.detailprofile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentDetailProfileBinding
import com.creativijaya.profilebook.databinding.ItemPostBinding
import com.creativijaya.profilebook.databinding.ItemPostTagBinding
import com.creativijaya.profilebook.domain.models.post.PostDto
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.ext.clickWithDebounce
import com.creativijaya.profilebook.util.ext.loadImageUrl
import com.creativijaya.profilebook.util.ext.toGone
import com.creativijaya.profilebook.util.ext.toVisible
import com.creativijaya.profilebook.util.widget.CommonRecyclerViewAdapter
import com.creativijaya.profilebook.util.widget.viewBinding

class DetailProfileFragment : BaseFragment(R.layout.fragment_detail_profile) {

    private val binding: FragmentDetailProfileBinding by viewBinding()
    private val viewModel: DetailProfileViewModel by fragmentViewModel()

    private val postAdapter: CommonRecyclerViewAdapter<PostDto> by lazy {
        CommonRecyclerViewAdapter(
            itemLayoutRes = R.layout.item_post,
            onBind = this::onBindPostItem,
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()

        viewModel.getDetailProfile()
        viewModel.getUserPost()

        subscribeToDetailProfile()
        subscribeToUserPost()
        subscribeToIsFriend()
    }

    private fun setupLayout() {
        with(binding) {
            toolbarDetailProfile.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            btnDetailProfileAddfriend.clickWithDebounce {
                viewModel.toggleAddFriend()
            }

            rvUserPosts.apply {
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
    }

    private fun subscribeToDetailProfile() {
        viewModel.onEach(
            DetailProfileState::profileDetailAsync,
            uniqueOnly()
        ) { async ->
            when (async) {
                Uninitialized, is Loading -> {

                }
                is Success -> {
                    val data = async.invoke()

                    with(binding) {
                        ivDetailProfilePicture.loadImageUrl(data.picture)
                        tvDetailProfileName.text = getString(
                            R.string.format_full_name,
                            data.title,
                            data.firstName,
                            data.lastName
                        )
                        tvDetailProfileGender.text = data.gender
                        tvDetailProfileBirthdate.text = data.dateOfBirth
                        tvDetailProfileJoinfrom.text = data.registerDate
                        tvDetailProfileEmail.text = data.email
                        tvDetailProfileAddress.text = data.address
                    }
                }
                is Fail -> handleError(async.error)
            }
        }
    }

    private fun subscribeToUserPost() {
        viewModel.onEach(
            DetailProfileState::userPostAsync,
            uniqueOnly()
        ) { async ->
            when (async) {
                Uninitialized, is Loading -> {
                    binding.shimmerUserPosts.apply {
                        toVisible()
                        startShimmer()
                    }
                    binding.rvUserPosts.toGone()
                }
                is Success -> {
                    binding.shimmerUserPosts.apply {
                        stopShimmer()
                        toGone()
                    }
                    binding.rvUserPosts.toVisible()

                    postAdapter.setData(async.invoke().data)
                }
                is Fail -> {
                    binding.shimmerUserPosts.apply {
                        stopShimmer()
                        toGone()
                    }
                    binding.rvUserPosts.toVisible()

                    handleError(async.error)
                }
            }
        }
    }

    private fun subscribeToIsFriend() {
        viewModel.onCommonAsync(DetailProfileState::isFriend) {
            val (bg, icon) = if (it) {
                R.drawable.bg_circle_blue to R.drawable.ic_friend
            } else {
                R.drawable.bg_circle_gray to R.drawable.ic_add_friend
            }

            binding.btnDetailProfileAddfriend.apply {
                setBackgroundResource(bg)
                setImageResource(icon)
            }
        }
    }
}
