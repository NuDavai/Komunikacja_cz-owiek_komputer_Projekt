package com.example.projectbudget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    fun validateInputs(): Boolean {
        var isValid = true

        if (login.isBlank()) {
            loginError = "Login nie może być pusty"
            isValid = false
        }

        if (password.isBlank()) {
            passwordError = "Hasło nie może być puste"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Hasło musi mieć co najmniej 6 znaków"
            isValid = false
        }

        return isValid
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_money),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Inteligentny Budżet Domowy",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF34A853), Color.Black),
                        start = Offset(0f, 0f),
                        end = Offset(2000f, 200f)
                    )
                )
            )

            Spacer(modifier = Modifier.height(100.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Zaloguj się",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF34A853), Color.Black),
                                start = Offset(0f, 0f),
                                end = Offset(2000f, 200f)
                            )
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = login,
                        onValueChange = {
                            login = it
                            loginError = null
                        },
                        label = { Text("Login") },
                        isError = loginError != null,
                        supportingText = {
                            loginError?.let { Text(it, color = Color.Red) }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = outlinedTextFieldColors()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = null
                        },
                        label = { Text("Hasło") },
                        isError = passwordError != null,
                        supportingText = {
                            passwordError?.let { Text(it, color = Color.Red) }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = outlinedTextFieldColors()
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF34A853), Color.Black),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, 300f)
                                ),
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable {
                                if (validateInputs()) {
                                    navController.navigate("home/${login}")
                                    {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Zaloguj się",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { /* Nawiguj do rejestracji */ }) {
                        Text("Nie masz konta? Zarejestruj się")
                    }
                }
            }
        }
    }
}

