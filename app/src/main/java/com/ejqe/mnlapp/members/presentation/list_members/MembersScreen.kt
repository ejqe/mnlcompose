package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ejqe.mnlapp.members.domain.model.Members


@Composable
fun MembersScreen(
    state: MembersScreenState,
    onItemClick: (name: String) -> Unit,
    navController: NavController
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
    ) {
        items(state.members) { members ->
            MemberItem(
                members,
                onItemClick = { name -> onItemClick(name) }
            )
        }
    }
}

@Composable
fun MemberItem(
    item: Members,
    onItemClick: (name: String) -> Unit
) {


    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .aspectRatio(0.9f)
            .padding(8.dp)
            .clickable { onItemClick(item.name) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)

        ) {
            MemberImage(
                imageUrl = item.imageUrl,
                modifier = Modifier

            )

            MemberName(
                name = item.name,
                fontSize = 12.sp
            )

        }
    }

}

@Composable
fun MemberName(name: String, fontSize: TextUnit ) {
    Text(
        text = name,
        style = MaterialTheme.typography.body2,
        fontSize = fontSize
    )
}

@Composable
fun MemberImage(imageUrl: String, modifier: Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "member_image",
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10))
    )


}





