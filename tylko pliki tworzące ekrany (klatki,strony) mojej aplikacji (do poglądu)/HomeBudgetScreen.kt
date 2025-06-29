package com.example.projectbudget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.budgetapp.ui.screens.BottomBarItem
import com.example.projectbudget.R

@Composable
fun HomeBudgetScreen(navController: NavHostController, currentRoute: String, login: String) {
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
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Inteligentny Budżet Domowy",
                fontSize = 42.sp,
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
        }

        // Główna przestrzeń – podzielona na napis + zdjęcie
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.67f)
        ) {
            // Sekcja: Witaj
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.10f)
                    .background(Color(0xFFF1F1FC)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Witaj $login!",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            // Sekcja: Zdjęcie
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.90f),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.animation.AnimatedVisibility(visible = true, enter = fadeIn()) {
                    Image(
                        painter = painterResource(id = R.drawable.welcomescreennews),
                        contentDescription = "Main image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        // Pasek dolny
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


