package com.ejqe.mnlapp.ui.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ejqe.mnlapp.members.presentation.list_members.MembersViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabLayout(
) {

    val viewModel: MembersViewModel = viewModel()
    val tabs = listOf("All", "Gen 1", "Gen 2", "Gen 3","Former Members")
    val tabIndex = viewModel.state.value.tabIndex
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(selectedTabIndex = tabIndex) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = tabIndex == index,

                onClick = {
                    coroutineScope.launch {
                        viewModel.setTab(newTabIndex = index) }
                          },
                text = { Text(title) }
            )
        }
    }

}




