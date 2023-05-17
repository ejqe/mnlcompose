package com.ejqe.mnlapp.members.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ejqe.mnlapp.ui.TopBar

@Composable
fun HomeScreen() {

Scaffold(
    topBar = {TopBar(title = "Home")}

) { paddingValues ->
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ){
        Text(text = "Home Screen")
    }

}


}