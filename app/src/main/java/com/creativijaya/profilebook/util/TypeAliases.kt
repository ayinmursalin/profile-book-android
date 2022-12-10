package com.creativijaya.profilebook.util

import com.creativijaya.profilebook.domain.models.dialog.DialogItemFilterDto
import com.creativijaya.profilebook.domain.models.dialog.DialogItemWrapper

typealias DialogItemSelectedListener<T> = (DialogItemWrapper<T>) -> Unit

typealias DialogSingleInputSubmittedListener = (String) -> Unit

typealias DialogDateSelectedListener = (Long) -> Unit

typealias DialogYearSelectedListener = (Int) -> Unit

typealias CommonDialogListener = () -> Unit

typealias DialogFilterListener = (DialogItemFilterDto) -> Unit

typealias DialogNoteSavedListener = (String) -> Unit
