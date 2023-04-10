package com.hackathon.equityafia.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hackathon.equityafia.R
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_clinics.presentation.viewmodels.ClinicsViewModel
import com.hackathon.equityafia.feature_home.components.ClinicCard
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: AuthViewModel,
    clinicsViewModel: ClinicsViewModel
) {

    EquityAfiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {

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
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
                            )
                            .fillMaxWidth()
                    ) {
                        val userName = viewModel.currentUser?.displayName
                        println("$userName is the user name")
                        Column {
                            Text(
                                text = "Hello $userName,",
                                modifier = Modifier
                                    .padding(20.dp),
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "Find a clinic",
                                modifier = Modifier
                                    .padding(top = 0.dp, start = 10.dp),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            val searchText = remember { mutableStateOf("") }

                            TextField(
                                value = searchText.value,
                                onValueChange = {
                                    searchText.value = it
                                },
                                label = { Text(text = "Search") },
                                placeholder = { Text(text = "") },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {/*TODO: Implement search*/ },
                                    onDone = null
                                ),
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                                        contentDescription = null,
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(8.dp)
                                    .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.small, clip = true)
                            )
                        }
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp),
                        content = {
                            val size = clinicsViewModel.clinics.value.size
                            items(size) { index ->
                                clinicsViewModel.clinics.value[index].let {
                                    Box(
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth()
                                    ) {
                                        ClinicCard(
                                            image = it.fields.image,
                                            clinicName = it.fields.name,
                                            clinicAddress = it.fields.address,
                                        )
                                    }


                                }

                            }
                        })
                }

            }

        }

    }

}
