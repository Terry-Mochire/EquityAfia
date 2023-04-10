package com.hackathon.equityafia.feature_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hackathon.equityafia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicCard(
    image: String,
    clinicName: String,
    clinicAddress: String,
) {
    ElevatedCard(
        onClick = {
            /*TODO*/
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight()
    ) {
        Box {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.comingsoon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Text(text = clinicName, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(8.dp))
                Text(text = clinicAddress, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 8.dp))

            }

        }

    }

}


@Preview
@Composable
fun ClinicCardPreview() {
    val image = "https://www.google.com"
    val clinicName = "Buruburu Clinic"
    val clinicAddress = "The Point Mall, Rabai Road"
    ClinicCard(image = image, clinicName = clinicName, clinicAddress = clinicAddress)
}