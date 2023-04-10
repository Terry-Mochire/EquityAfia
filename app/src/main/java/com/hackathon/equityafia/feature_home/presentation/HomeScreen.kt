package com.hackathon.equityafia.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hackathon.equityafia.R
import com.hackathon.equityafia.feature_auth.presentation.AuthRepository
import com.hackathon.equityafia.feature_auth.presentation.AuthViewModel
import com.hackathon.equityafia.feature_home.components.ClinicCard
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    EquityAfiaTheme(
        darkTheme = true
    ) {
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
                                            contentDescription = null )
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
                    ){
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
                                text = "Find a clinic near you",
                                modifier = Modifier
                                    .padding(top = 0.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                                    .align(Alignment.CenterHorizontally)
                                ,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(10.dp),
                        content = {
                            items(10){
                                Box(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                ){
                                    ClinicCard(

                                    )
                                }
                            }

                        } )

                }
            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val repository = AuthRepository()
    val viewModel = AuthViewModel(repository)
    HomeScreen(navController, viewModel)
}