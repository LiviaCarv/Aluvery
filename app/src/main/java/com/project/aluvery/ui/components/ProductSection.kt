package com.project.aluvery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.aluvery.model.Product
import com.project.aluvery.sample.sampleCandies

@Composable
fun ProductSection(
    title: String,
    productsList: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(productsList) { prod ->
                ProductItem(product = prod)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun ProductSectionPreview() {
    ProductSection("This is a section", sampleCandies)
}
