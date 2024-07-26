package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val amount: Int,
    @DrawableRes val imageResourceId: Int,
)