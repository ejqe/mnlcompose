package com.ejqe.mnlapp.members.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.ejqe.mnlapp.members.presentation.navigation.SetUpNavGraph
import com.ejqe.mnlapp.ui.widgets.BottomBar
import com.ejqe.mnlapp.ui.TopBar


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("") }

    LaunchedEffect(navController.currentBackStackEntryFlow) {
        navController.currentBackStackEntryFlow.collect {
            title = it.destination.route ?: ""
        }
    }

    Scaffold(
        topBar = {TopBar(title = title)},
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValues ->
        SetUpNavGraph(navController = navController, paddingValues = paddingValues ) }


}



