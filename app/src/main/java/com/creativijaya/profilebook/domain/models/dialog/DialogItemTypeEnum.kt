package com.creativijaya.profilebook.domain.models.dialog

enum class DialogItemTypeEnum(val key: String) {
    TEXT("TEXT"),
    NUMBER_ONLY("NUMBER_ONLY"),
    PASSWORD("PASSWORD"),
    MULTILINE("MULTILINE");

    companion object {
        @JvmStatic
        fun fromString(key: String?) = values().find {
            it.key == key
        } ?: TEXT
    }
}
