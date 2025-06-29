package com.example.budgetapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projectbudget.R

@Composable
fun PriceAnalystScreen(navController: NavHostController, currentRoute: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Pasek czarny
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.03f)
                .background(Color.Black)
        )

        // Pasek górny
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
                .background(Color(0xFF50481C)),
        ) {
            var menuExpanded by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Analityk Cen",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF34A853), Color.Black),
                            start = Offset(0f, 0f),
                            end = Offset(1000f, 0f)
                        )
                    )
                )

                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ikona_menu),
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(62.dp)
                            .clickable { menuExpanded = true }
                    )

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        modifier = Modifier
                            .width(380.dp)
                            .background(Color(0xFFF1F1FC))
                    ) {
                        DropdownMenuItem(
                            text = { Text("Historia cen") },
                            onClick = {
                                // TODO: Dodaj funkcję
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Więcej szczegółów") },
                            onClick = {
                                // TODO: Dodaj funkcję
                                menuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Wyloguj") },
                            onClick = {
                                navController.navigate("login") {
                                    popUpTo(0) { inclusive = true }
                                }
                                menuExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // Główna przestrzeń
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.67f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.porownanie_cen), // zamień na właściwy zasób
                contentDescription = "Analityka Cen",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Pasek dolny - jeśli jest, bez weight
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
                .background(Color(0xFF50481C))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(R.drawable.menadzer_rachunkow, "Rachunki", "bills", navController, currentRoute)
                BottomBarItem(R.drawable.analityk_cen, "Ceny", "prices", navController, currentRoute)
                BottomBarItem(R.drawable.skaner_paragonow, "Skaner", "scanner", navController, currentRoute)
            }
        }
    }
}
