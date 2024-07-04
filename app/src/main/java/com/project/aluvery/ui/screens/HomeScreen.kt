package com.project.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.aluvery.R
import com.project.aluvery.model.Product
import com.project.aluvery.sample.sampleSections
import com.project.aluvery.ui.components.CardProductItem
import com.project.aluvery.ui.components.ProductSection
import com.project.aluvery.ui.components.SearchBar

class HomeScreenUiState(
    val sections: Map<String, List<Product>>,
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isFilterActive(): Boolean {
        return searchText.isNotBlank()
    }

}

@Composable
fun HomeScreen(
    state: HomeScreenUiState,
    modifier: Modifier = Modifier
) {
    val text = state.searchText
    val filter = state.searchedProducts
    val sections = state.sections

    Column(modifier) {

        SearchBar(text = text, onValueChange = { newText -> state.onSearchChange(newText) })

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.isFilterActive()) {
                if (filter.isEmpty()) {
                    item {
                        Text(
                            text = stringResource(R.string.empty_list),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                } else {
                    items(filter) {
                        CardProductItem(
                            product = it,
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                    }
                }

            } else {
                sections.forEach { section ->
                    item {
                        ProductSection(title = section.key, productsList = section.value)
                    }
                }

            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(state = HomeScreenUiState(sampleSections, searchText = "AA"))
}