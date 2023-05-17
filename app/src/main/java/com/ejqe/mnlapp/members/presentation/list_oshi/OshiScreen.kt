package com.ejqe.mnlapp.members.presentation.list_oshi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejqe.mnlapp.members.domain.model.Members
import com.ejqe.mnlapp.members.presentation.list_members.MemberImage
import com.ejqe.mnlapp.members.presentation.list_members.MemberName
import com.ejqe.mnlapp.ui.TopBar
import com.ejqe.mnlapp.ui.theme.MnlappTheme

@Composable
fun OshiScreen(
    items: List<Members>
) {
    Scaffold(
        topBar = { TopBar(title = "My Oshi List") }
    ) { paddingValues ->
        LazyColumn {
            items(items) { members ->
                OshiItem(
                    item = members,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun OshiItem(item: Members, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .clickable(onClick = { })
            .padding(16.dp)
    ) {
        MemberImage(
            imageUrl = item.imageUrl,
            modifier = modifier
        )
        MemberName(name = item.name, fontSize = 32.sp)
    }

}

@Preview
@Composable
fun OshiScreenPreview() {
    MnlappTheme {
    }

}
