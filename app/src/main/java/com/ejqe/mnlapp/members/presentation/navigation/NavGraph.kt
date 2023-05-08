package com.ejqe.mnlapp.members.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ejqe.mnlapp.MembersScreen
import com.ejqe.mnlapp.members.presentation.details.DetailsScreen
import com.ejqe.mnlapp.members.presentation.details.DetailsViewModel
import com.ejqe.mnlapp.members.presentation.list_members.MembersViewModel

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "members_screen"
    ) {



        composable(route = Screen.Members.route) {
            val viewModel: MembersViewModel = hiltViewModel()
            MembersScreen(
                state = viewModel.state.value,
                onItemClick = { name: String -> navController.navigate("${Screen.Detail.route}/$name") },
                onOshiClick = { name, oldValue -> viewModel.toggleOshiFavorite(name, oldValue) },
                navController = navController

            )
        }

        composable(
            route = "${Screen.Detail.route}/{member_name}",
            arguments = listOf(
                navArgument("member_name") {
                    type = NavType.StringType
                })
        ) {
            val viewModel: DetailsViewModel = hiltViewModel()
            val item = viewModel.state.value
            if (item != null) {
                DetailsScreen(item = item)
            }
        }
    }
}