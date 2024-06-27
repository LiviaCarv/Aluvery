package com.project.aluvery.model

import androidx.annotation.DrawableRes
import com.project.aluvery.R
import java.math.BigDecimal

class Product(
    val description: String,
    val price: BigDecimal,
    @DrawableRes val image: Int = R.drawable.ic_launcher_background
)
