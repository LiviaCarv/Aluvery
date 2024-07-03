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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.aluvery.R
import com.project.aluvery.model.Product
import com.project.aluvery.sample.sampleProducts
import com.project.aluvery.sample.sampleSections
import com.project.aluvery.ui.components.CardProductItem
import com.project.aluvery.ui.components.ProductSection
import com.project.aluvery.ui.components.SearchBar

class HomeScreenUiState(
    val sections: Map<String, List<Product>>,
    private val products: List<Product> = emptyList(),
    searchText: String = ""
) {
    var text by mutableStateOf(searchText)
        private set
    val filter
        get() = if (text.isNotBlank()) {
            sampleProducts.filter {
                containsInNameOrDescription(it)
            } + products.filter {
                containsInNameOrDescription(it)
            }
        } else emptyList()

    private fun containsInNameOrDescription(prod: Product) =
        prod.name.contains(text, ignoreCase = true) || prod.description?.contains(
            text,
            ignoreCase = true
        ) ?: false

    fun isFilterActive(): Boolean {
        return text.isNotBlank()
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        text = searchText
    }
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState,
    modifier: Modifier = Modifier
) {
    val text = state.text
    val filter = remember(text) {
        state.filter
    }
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