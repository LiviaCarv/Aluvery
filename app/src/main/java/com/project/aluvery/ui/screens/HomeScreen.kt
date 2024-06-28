package com.project.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.aluvery.model.Product
import com.project.aluvery.sample.sampleProducts
import com.project.aluvery.sample.sampleSections
import com.project.aluvery.ui.components.CardProductItem
import com.project.aluvery.ui.components.ProductSection
import com.project.aluvery.ui.components.SearchBar

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        SearchBar()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sampleProducts) {
                CardProductItem(
                    product = it,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
//            sections.forEach { section ->
//                item {
//                    ProductSection(title = section.key, productsList = section.value)
//                }
//            }


        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(sampleSections)
}