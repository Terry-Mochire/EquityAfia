package com.hackathon.equityafia.feature_clinics.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hackathon.equityafia.R
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.ClinicResponseItem
import com.hackathon.equityafia.feature_clinics.presentation.viewmodels.ClinicsViewModel
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicDetailsScreen(
    clinic: ClinicResponseItem,
    clinicsViewModel: ClinicsViewModel,
    navController: NavHostController,
    viewModel: AuthViewModel
    ) {
    EquityAfiaTheme() {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                                Text(text = "Clinic Details")
                        },
                        navigationIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                contentDescription = ""
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        actions = {
                            val expanded = remember { mutableStateOf(false) }
                            IconButton(onClick = { expanded.value = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
                                    contentDescription = ""
                                )
                            }
                            DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false }
                            ) {
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.firebaseSignOut()
                                        navController.navigate(Screens.SignInScreen.route){
                                            popUpTo(Screens.HomeScreen.route){
                                                inclusive = true
                                            }
                                        }
                                    },
                                    text = { Text(text = "Log Out") },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_logout),
                                            contentDescription = null
                                        )
                                    }
                                )
                                DropdownMenuItem(
                                    onClick = {
                                        navController.navigate(Screens.ProfileScreen.route) {
                                            popUpTo(Screens.HomeScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    },
                                    text = { Text(text = "Edit Profile") },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }

                    )

                },
            ) {innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(clinic.fields.image)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.comingsoon),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )

                    Text(
                        text = clinic.fields.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp))

                    Box{
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                                ){
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(60.dp)
                            )
                            Column{
                                Text(
                                    text = clinic.fields.doctor_in_charge,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Start,
                                )
                                Text(
                                    text = "Doctor in Charge",
                                    style = MaterialTheme.typography.labelSmall,
                                    textAlign = TextAlign.Start,
                                )
                            }
                        }
                    }
                    LazyColumn(
                        content = {
                            item {
                                Row {
                                    Text(text = "Address: ", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))

                                    Text(text = clinic.fields.address, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))

                                }
                            }

                            item {
                                Row {
                                    Text(text = "Email: ", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))

                                    Text(text = clinic.fields.email, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))

                                }
                            }

                            item {
                                Row{
                                    Text(text = "Phone: ", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))

                                    Text(text = clinic.fields.telephone, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                                }

                            }

                            item {

                                Text(text = "List of Services", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline)

                            }

                            clinicsViewModel.getServicesInAClinic(clinic.pk)
                            val size = clinicsViewModel.services.value.size
                            items(size) { index ->
                                val service = clinicsViewModel.services.value[index]
                                Text(text = "${index+1}. ${service.fields.name}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(16.dp))
                            }

                            item {
                                Button(
                                    onClick = {
                                        /*TODO*/
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(text = "Book Appointment")
                                }
                            }
                        }
                    )


                }

            }

        }

    }

}
