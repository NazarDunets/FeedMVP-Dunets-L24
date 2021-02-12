package com.example.feedmvp_dunets_l24.model

import androidx.annotation.ColorInt

data class PostUiModel(
    val userId: Int,
    val title: String,
    val body: String,
    val hasWarning: Boolean,
    val isBanned: Boolean,
    @ColorInt val bgColorInt: Int
)