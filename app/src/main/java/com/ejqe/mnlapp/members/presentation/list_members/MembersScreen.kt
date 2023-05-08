package com.ejqe.mnlapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ejqe.mnlapp.members.domain.Members
import com.ejqe.mnlapp.members.presentation.list_members.MembersScreenState
import com.ejqe.mnlapp.ui.theme.MnlappTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun MembersScreen(
    state: MembersScreenState,
    onItemClick: (name: String) -> Unit,
    onOshiClick: (name: String, OldValue: Boolean) -> Unit,
    navController: NavController
) {

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        items(state.members) { members ->
            MemberItem(
                members,
                onItemClick = { name -> onItemClick(name) },
                onOshiClick = { name, OldValue -> onOshiClick(name, OldValue) }
            )
        }
    }
}

@Composable
fun MemberItem(
    item: Members,
    onItemClick: (name: String) -> Unit,
    onOshiClick: (name: String, OldValue: Boolean) -> Unit
) {
    val icon =  if (item.isOshi) Icons.Filled.Favorite
    else Icons.Filled.FavoriteBorder

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.name) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)

        ) {
            MemberImage(item.imageUrl)
            MemberName(item.name)
            OshiIcon(icon, modifier = Modifier.weight(0.15f))
                 { onOshiClick(item.name, item.isOshi)}

        }
    }

}

@Composable
fun MemberName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun MemberImage(imageUrl: String) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        ),
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
    )
}

@Composable
fun OshiIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {


    Image(
        imageVector = icon,
        contentDescription = "oshi icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() }
    )
}
