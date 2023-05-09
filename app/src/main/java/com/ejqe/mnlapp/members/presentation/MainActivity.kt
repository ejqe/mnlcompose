package com.ejqe.mnlapp.members.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ejqe.mnlapp.members.presentation.navigation.SetUpNavGraph
import com.ejqe.mnlapp.ui.theme.MnlappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MnlappTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)

            }
        }
    }
}


