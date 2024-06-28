package com.project.aluvery.model

import androidx.annotation.DrawableRes
import com.project.aluvery.R
import java.math.BigDecimal

class Product(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val description: String? = null
)
