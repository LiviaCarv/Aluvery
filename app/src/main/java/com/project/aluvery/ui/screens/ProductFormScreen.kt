package com.project.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.aluvery.R
import com.project.aluvery.model.Product
import com.project.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal
import java.text.DecimalFormat

// STATE HOLDER
class ProductFormScreenUiState(
    val url: String = "",
    val productName: String = "",
    val description: String = "",
    val price: String = "",
    val onUrlChange: (String) -> Unit = {},
    val onProductNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {}
)

// COMPOSABLE STATEFUL
@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {},
) {
    var url by rememberSaveable { mutableStateOf("") }
    var productName by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    val formatter = remember {
        DecimalFormat("#.##")
    }

    ProductFormScreen(
        state = ProductFormScreenUiState(
            url = url,
            productName = productName,
            price = price,
            description = description,
            onUrlChange = {
                url = it
            },
            onProductNameChange = {
                productName = it
            },
            onPriceChange = {
                try {
                    price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        price = it
                    }
                }
            },
            onDescriptionChange = {
                description = it
            }
        ),
        onSaveClick = {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Product(
                name = productName,
                image = url,
                price = convertedPrice,
                description = description
            )
            onSaveClick(product)
        }
    )
}

// COMPOSABLE STATELESS
@Composable
fun ProductFormScreen(
    state: ProductFormScreenUiState,
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit = {}
) {

    val url = state.url
    val productName = state.productName
    val description = state.description
    val price = state.price

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Create new product",
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )

        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                error = painterResource(id = R.drawable.placeholder),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                onSuccess = {

                }
            )
        }

        TextField(
            value = url,
            onValueChange = { newUrl -> state.onUrlChange(newUrl) },
            label = { Text(text = "Image URL") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = productName,
            onValueChange = { newName -> state.onProductNameChange(newName) },
            label = { Text(text = "Product Name") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = price,
            onValueChange = { newPrice ->
                state.onPriceChange(newPrice)

            },
            label = { Text(text = "Price") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { newDescription -> state.onDescriptionChange(newDescription) },
            label = { Text(text = "Description") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            maxLines = 8,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
        )

        Button(
            onClick = onSaveClick,
            Modifier.fillMaxWidth(),
        ) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
//            ProductFormScreen()
        }
    }
}