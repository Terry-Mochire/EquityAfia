package com.hackathon.equityafia.feature_clinics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hackathon.equityafia.R
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Location
import com.hackathon.equityafia.feature_clinics.presentation.viewmodels.ClinicsViewModel
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsFormScreen(
    clinicsViewModel: ClinicsViewModel,
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    EquityAfiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Enter Location Details")
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
                            }
                        })
                },

                ) { innerPadding ->

                val active = remember { mutableStateOf(false) }
                val active2 = remember { mutableStateOf(false) }
                val county = remember { mutableStateOf("") }
                val countryId = remember { mutableStateOf(1) }
                val countyId = remember { mutableStateOf(1) }
                val sub_county = remember { mutableStateOf("") }
                val sub_countyId = remember { mutableStateOf(1) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary
                            )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        value = "Kenya",
                        onValueChange = {

                        },
                        label = { Text(text = "Country") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    SearchBar(
                        query = county.value,
                        onQueryChange = { county.value = it },
                        active = active.value,
                        onActiveChange = { active.value = it },
                        onSearch = { active.value = false },
                        placeholder = { Text(text = "County") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            val size = clinicsViewModel.counties.value.size
                            items(size) { idx ->
                                clinicsViewModel.counties.value[idx].let {
                                    ListItem(
                                        headlineContent = { Text(it.fields.name) },
                                        modifier = Modifier.clickable {
                                            county.value = it.fields.name
                                            countyId.value = it.pk
                                            active.value = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    SearchBar(
                        query = sub_county.value,
                        onQueryChange = { sub_county.value = it },
                        active = active2.value,
                        onActiveChange = { active2.value = it },
                        onSearch = { active2.value = false },
                        placeholder = { Text(text = "Sub County") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            clinicsViewModel.getSubCounties(countyId.value)

                            val size = clinicsViewModel.subCounties.value.size

                            println("SIZE: $size")

                            items(size) { idx ->
                                clinicsViewModel.subCounties.value[idx].let {
                                    ListItem(
                                        headlineContent = { Text(it.fields.name) },
                                        modifier = Modifier.clickable {
                                            sub_county.value = it.fields.name
                                            sub_countyId.value = it.pk
                                            active2.value = false
                                        }
                                    )
                                }
                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(100.dp))
                    Button(
                        onClick = {
                            val location = Location(
                                county = countyId.value,
                                sub_county = sub_countyId.value,
                                country = countryId.value
                            )
                            clinicsViewModel.getAllClinicsInALocation(location)
                            navController.navigate(Screens.HomeScreen.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Find Clinic")
                    }

                }

            }

        }

    }
}






