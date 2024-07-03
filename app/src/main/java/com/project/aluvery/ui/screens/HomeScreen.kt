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
import androidx.compose.runtime.saveable.rememberSaveable
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

class HomeScreenUiState(searchText: String = "") {
    var text by mutableStateOf(searchText)
    val filter
        get() = if (text.isNotBlank()) {
            sampleProducts.filter {
                it.name.contains(text, ignoreCase = true) || it.description?.contains(
                    text,
                    ignoreCase = true
                ) ?: false
            }
        } else emptyList()

    fun isFilterActive() : Boolean {
        return text.isNotBlank()
    }
}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    modifier: Modifier = Modifier,
    searchText: String = ""
) {
    val state = remember {
        HomeScreenUiState(searchText)
    }
    val text = state.text
    val filter = remember(text) {
        state.filter
    }

    Column(modifier) {

        SearchBar(text, { newValue ->
            state.text = newValue
        })

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
    HomeScreen(sampleSections, searchText = "aaaa")
}