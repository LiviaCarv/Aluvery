package com.project.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.aluvery.R
import com.project.aluvery.extensions.toBrazilianCurrency
import com.project.aluvery.model.Product
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: CardElevation = CardDefaults.cardElevation(4.dp)
) {
    var expandedDescription by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .clickable { expandedDescription = !expandedDescription },
        elevation = elevation
    ) {
        Column {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop,
            )

            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = product.name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = product.price.toBrazilianCurrency(), color = Color.White)
            }
            val textOverflow =
                if (expandedDescription) TextOverflow.Visible
                else TextOverflow.Ellipsis
            val maxLines =
                if (expandedDescription) Int.MAX_VALUE
                else 2


            product.description?.let {
                Text(
                    text = product.description,
                    modifier = Modifier.padding(10.dp),
                    maxLines = maxLines,
                    overflow = textOverflow
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardProductItemPreview() {
    CardProductItem(
        Product(
            name = "Chocolate",
            price = BigDecimal("3.99"),
            description = LoremIpsum(50).values.first(),
            image = "https://images.pexels.com/photos/65882/chocolate-dark-coffee-confiserie-65882.jpeg",
        )
    )
}