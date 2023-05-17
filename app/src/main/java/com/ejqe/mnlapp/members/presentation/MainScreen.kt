package com.ejqe.mnlapp.members.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ejqe.mnlapp.members.presentation.navigation.SetUpNavGraph
import com.ejqe.mnlapp.ui.BottomBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ){ SetUpNavGraph(navController = navController) }
}


