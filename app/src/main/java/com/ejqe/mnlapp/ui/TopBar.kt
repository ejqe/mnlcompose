package com.ejqe.mnlapp.ui

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopBar(title: String) {
    TopAppBar(
        backgroundColor = Color.White
    ) { Text(text = title, color = Color.Black) }
}

