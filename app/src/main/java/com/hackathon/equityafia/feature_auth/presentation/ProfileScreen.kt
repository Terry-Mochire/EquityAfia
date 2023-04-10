package com.hackathon.equityafia.feature_auth.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hackathon.equityafia.R
import com.hackathon.equityafia.feature_navigation.Screens
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val context = LocalContext.current

    EquityAfiaTheme{
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Edit Profile")
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
                                    onClick = { /*TODO*/ },
                                    text = { Text(text = "Log Out") },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_logout),
                                            contentDescription = null )
                                    }
                                )
                            }
                        })
                },

            ) { innerPadding->

                val email = remember { mutableStateOf(TextFieldValue()) }
                val phoneNumber = remember { mutableStateOf(TextFieldValue()) }
                val displayName = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf("") }
                val confirmPassword = remember { mutableStateOf("") }

                val resetPassword = remember { mutableStateOf(false) }

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
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text(text = "Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    OutlinedTextField(
                        value = displayName.value,
                        onValueChange = { displayName.value = it },
                        label = { Text(text = "User Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )

                    OutlinedTextField(
                        value = phoneNumber.value,
                        onValueChange = { phoneNumber.value = it },
                        label = { Text(text = "Phone Number") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )

                    if (resetPassword.value) {
                        OutlinedTextField(
                            value = password.value,
                            onValueChange = { password.value = it },
                            label = { Text(text = "New Password") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                        OutlinedTextField(
                            value = confirmPassword.value,
                            onValueChange = { confirmPassword.value = it },
                            label = { Text(text = "Confirm Password") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                                  val response = viewModel.updateUserDetails(
                                      name = displayName.value.text,
                                        email = email.value.text,
                                        phone = phoneNumber.value.text,
                                        password = password.value,
                                        password2 = confirmPassword.value
                                  )

                            if (response){
                                Toast.makeText(
                                    context,
                                    "Profile Updated",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(Screens.HomeScreen.route){
                                    popUpTo(Screens.HomeScreen.route){
                                        inclusive = true
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Save")
                    }

                    Box(modifier = Modifier.fillMaxSize()) {
                        ClickableText(
                            text = AnnotatedString("Reset your password?"),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(20.dp),
                            onClick = {
                                resetPassword.value = true
                            },
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.SansSerif,
                                textDecoration = TextDecoration.Underline,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }


                }

            }

        }

    }


}


@Preview
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    val repository = AuthRepository()
    val viewModel = AuthViewModel(repository)
    ProfileScreen(navController, viewModel)
}