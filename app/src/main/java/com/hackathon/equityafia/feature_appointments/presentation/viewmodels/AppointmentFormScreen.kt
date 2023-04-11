package com.hackathon.equityafia.feature_appointments.presentation.viewmodels

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hackathon.equityafia.R
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_clinics.data.remote.models.requests.Booking
import com.hackathon.equityafia.feature_clinics.data.remote.models.responses.ClinicResponseItem
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentFormScreen(
    viewModel: AppointmentsViewModel,
    navController: NavController,
    authViewModel: AuthViewModel,
    clinic: ClinicResponseItem
) {
    EquityAfiaTheme {

        val context = LocalContext.current

        var response = remember {
            mutableStateOf("")
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Enter Booking Details")
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
                                        authViewModel.firebaseSignOut()
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

                ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var date = remember { mutableStateOf("") }
                        var activeDate = remember { mutableStateOf(false) }

                        SearchBar(
                            query = date.value,
                            onQueryChange = { date.value = it },
                            active = activeDate.value,
                            onActiveChange = { activeDate.value = it },
                            onSearch = { activeDate.value = false },
                            placeholder = { Text(text = "Date") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                val size = viewModel.availableDates.value.dates.size
                                items(size) { idx ->
                                    viewModel.availableDates.value.dates[idx].let {
                                        ListItem(
                                            headlineContent = { Text(it) },
                                            modifier = Modifier.clickable {
                                                date.value = it
                                                activeDate.value = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        var time = remember { mutableStateOf("") }
                        var activetime = remember { mutableStateOf(false) }

                        SearchBar(
                            query = time.value,
                            onQueryChange = { time.value = it },
                            active = activetime.value,
                            onActiveChange = { activetime.value = it },
                            onSearch = { activetime.value = false },
                            placeholder = { Text(text = "Time") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                val size = viewModel.availableTimes.value.hours.size
                                items(size) { idx ->
                                    viewModel.availableTimes.value.hours[idx].let {
                                        ListItem(
                                            headlineContent = { Text(it) },
                                            modifier = Modifier.clickable {
                                                time.value = it
                                                activetime.value = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        var service = remember { mutableStateOf("") }
                        var activeService = remember { mutableStateOf(false) }

                        SearchBar(
                            query = service.value,
                            onQueryChange = { service.value = it },
                            active = activeService.value,
                            onActiveChange = { activeService.value = it },
                            onSearch = { activeService.value = false },
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
                                val exampleOfServices = listOf("Doctor Consultation", "Dental", "Optical", "Pharmacy", "Lab", "Other")
                                items(exampleOfServices.size) { idx ->
                                    exampleOfServices[idx].let {
                                        ListItem(
                                            headlineContent = { Text(it) },
                                            modifier = Modifier.clickable {
                                                service.value = it
                                                activeService.value = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Button(onClick = {
                            val booking = authViewModel.currentUser?.let { it1 ->
                                Booking(
                                    clinic = clinic.fields.name,
                                    day = date.value,
                                    time = time.value,
                                    service = service.value,
                                    user_id = it1.uid
                                )
                            }

                            if (booking != null) {
                                viewModel.bookAppointment(booking)
                            }
                        response = viewModel.bookingResponse
                            Toast.makeText(context, response.value, Toast.LENGTH_SHORT).show()

                        }) {
                            Text("Save")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                    }

                }

            }

        }
    }

}