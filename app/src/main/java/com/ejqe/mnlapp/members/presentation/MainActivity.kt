package com.ejqe.mnlapp.members.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ejqe.mnlapp.members.presentation.navigation.SetUpNavGraph
import com.ejqe.mnlapp.ui.theme.MnlappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MnlappTheme {
                MainScreen()

            }
        }
    }
}


