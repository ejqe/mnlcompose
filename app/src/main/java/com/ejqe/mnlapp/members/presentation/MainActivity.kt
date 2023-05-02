package com.ejqe.mnlapp.members.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ejqe.mnlapp.MembersScreen
import com.ejqe.mnlapp.members.presentation.list.MembersViewModel
import com.ejqe.mnlapp.ui.theme.MnlappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MnlappTheme {
                MnlApp()

            }
        }
    }

    @Composable
    fun MnlApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "Members") {
            composable(route = "Members") {
                val viewModel: MembersViewModel = hiltViewModel()
                MembersScreen(
                    state = viewModel.state.value,
                    onItemClick = {},
                    onOshiClick = { name, oldValue -> viewModel.toggleOshi(name, oldValue)}
                )
            }
        }

    }

}

