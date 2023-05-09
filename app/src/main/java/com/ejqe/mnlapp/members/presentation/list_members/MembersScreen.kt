package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ejqe.mnlapp.members.domain.Members
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun MembersScreen(
    state: MembersScreenState,
    onItemClick: (name: String) -> Unit,
    onOshiClick: (name: String, OldValue: Boolean) -> Unit,
    navController: NavController
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
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
    onOshiClick: (name: String, oldValue: Boolean) -> Unit
) {
    val icon =  if (item.isOshi) Icons.Filled.Favorite
    else Icons.Filled.FavoriteBorder

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(item.name) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)

        ) {
            MemberImage(
                item.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(4.dp)

            )
            Row {
                MemberName(item.name)
                OshiIcon(icon, modifier = Modifier.weight(0.15f))
                { onOshiClick(item.name, item.isOshi)}
            }


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
fun MemberImage(imageUrl: String, modifier: Modifier) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
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
