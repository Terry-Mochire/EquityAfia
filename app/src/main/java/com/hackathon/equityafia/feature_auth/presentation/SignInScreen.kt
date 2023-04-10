package com.hackathon.equityafia.feature_auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
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

@Composable
fun SignInScreen(
    navController: NavHostController,
    repository: AuthRepository,
    viewModel: AuthViewModel = AuthViewModel(repository)
) {
    EquityAfiaTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("Forgot password?"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    onClick = { },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = SansSerif,
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val email = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Button(
                    onClick = {
                        val response = viewModel.firebaseSignInWithEmailAndPassword(
                            email.value.text,
                            password.value.text
                        )
                        if (response) {
                            navController.navigate(Screens.HomeScreen.route)
                        }
                    }
                ) {
                    Text("Sign in")
                }

//                Text(text = "Or sign with", modifier = Modifier.padding(10.dp))
//
//                Button(
//                    onClick = { /*TODO*/ }
//                ) {
//                    Row {
//                        Image(
//                            painter = painterResource(id = R.drawable.google),
//                            contentDescription = "",
//                            modifier = Modifier
//                                .size(20.dp)
//                                .align(Alignment.CenterVertically)
//                        )
//
//                        Text(
//                            text = "Google",
//                            modifier = Modifier.padding(start = 10.dp)
//                        )
//
//                    }
//                }

            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenPreview() {
//    val navController = rememberNavController()
//    SignInScreen(navController, )
}