package com.example.a30daysofphotography.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Challenge(
    @StringRes val dayIndicatorRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)