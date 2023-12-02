package com.ejqe.mnlapp.members.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ejqe.mnlapp.members.presentation.details.DetailsScreen
import com.ejqe.mnlapp.members.presentation.details.DetailsViewModel
import com.ejqe.mnlapp.members.presentation.home.HomeScreen
import com.ejqe.mnlapp.members.presentation.list_members.MembersScreen
import com.ejqe.mnlapp.members.presentation.list_members.MembersViewModel
import com.ejqe.mnlapp.members.presentation.list_oshi.OshiScreen
import com.ejqe.mnlapp.members.presentation.list_oshi.OshiViewModel
import com.ejqe.mnlapp.ui.Screen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {


        composable(route = Screen.Members.route) {
            val viewModel: MembersViewModel = hiltViewModel()


            MembersScreen(
                state = viewModel.state.value,
                paddingValues = paddingValues,
                onItemClick = { name: String ->
                    navController.navigate("${Screen.Detail.route}/$name")
                }

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
                DetailsScreen(
                    item = item,
                    onButtonClick = { navController.navigate(Screen.Oshi.route) },
                    onOshiClick = { name, oldValue ->
                        viewModel.toggleOshiFavorite(name, oldValue)
                    }
                )
            }
        }


        composable(route = Screen.Oshi.route) {
            val viewModel: OshiViewModel = hiltViewModel()
            OshiScreen(items = viewModel.state.value)
        }

        composable(route = Screen.Home.route) {
            HomeScreen()

        }
    }
}