package com.creativijaya.profilebook.presentation.main.detailprofile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.fragmentViewModel
import com.creativijaya.profilebook.R
import com.creativijaya.profilebook.databinding.FragmentDetailProfileBinding
import com.creativijaya.profilebook.presentation.base.BaseFragment
import com.creativijaya.profilebook.util.ext.loadImageUrl
import com.creativijaya.profilebook.util.widget.viewBinding

class DetailProfileFragment : BaseFragment(R.layout.fragment_detail_profile) {

    private val binding: FragmentDetailProfileBinding by viewBinding()
    private val viewModel: DetailProfileViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLayout()

        viewModel.getDetailProfile()

        subscribeToDetailProfile()
    }

    private fun setupLayout() {
        with(binding) {
            toolbarDetailProfile.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun invalidate() {

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
}
