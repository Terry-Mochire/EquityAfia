package com.hackathon.equityafia.feature_auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackathon.equityafia.R

@Composable
fun SignUpScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Already have an account? Sign in"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { /*TODO*/ },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
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
        val newUserName = remember { mutableStateOf(TextFieldValue()) }
        val newPassword = remember { mutableStateOf(TextFieldValue()) }
        val confirmPassword = remember { mutableStateOf(TextFieldValue()) }
        val email = remember { mutableStateOf(TextFieldValue()) }

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        OutlinedTextField(
            value = newUserName.value,
            onValueChange = { newUserName.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        OutlinedTextField(
            value = newPassword.value,
            onValueChange = { newPassword.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Button(onClick = { /*TODO*/ }) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Or Sign Up with")

        Button(onClick = { /*TODO*/ }) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                )

                Text(
                    text = "Google",
                    modifier = Modifier.padding(start = 10.dp)
                )

            }

        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}