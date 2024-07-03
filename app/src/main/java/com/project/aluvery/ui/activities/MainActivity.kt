package com.project.aluvery.ui.activities

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.aluvery.dao.ProductDao
import com.project.aluvery.sample.sampleCandies
import com.project.aluvery.sample.sampleDrinks
import com.project.aluvery.sample.sampleSections
import com.project.aluvery.ui.screens.HomeScreen
import com.project.aluvery.ui.screens.HomeScreenUiState
import com.project.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    private val dao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val products = dao.getProducts()
                val sections = mapOf(
                    "Products" to products,
                    "Promotional Items" to sampleCandies + sampleDrinks,
                    "Candies" to sampleCandies,
                    "Drinks" to sampleDrinks,

                    )
                val state = remember(products) {
                    HomeScreenUiState(sections, products = products)
                }
                HomeScreen(state = state)
            }
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = onFabClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add new product"
                        )
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }

        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App(content = { HomeScreen(HomeScreenUiState(sampleSections)) })
}

