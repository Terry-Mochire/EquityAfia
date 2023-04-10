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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.equityafia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicCard() {
    ElevatedCard(
        onClick = {
            /*TODO*/
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(170.dp)
            .wrapContentHeight()
    ) {
        Box {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.buruburu),
                    contentDescription = "Clinic Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Text(text = "Buruburu Clinic", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(8.dp))
                Text(text = "The Point Mall, Rabai Road", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 8.dp))

            }

        }

    }

}


@Preview
@Composable
fun ClinicCardPreview() {
    ClinicCard()
}