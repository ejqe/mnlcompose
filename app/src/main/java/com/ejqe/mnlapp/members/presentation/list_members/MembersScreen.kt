package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ejqe.mnlapp.members.domain.model.Members
import com.ejqe.mnlapp.ui.TopBar


@Composable
fun MembersScreen(
    state: MembersScreenState,
    onItemClick: (name: String) -> Unit,
) {
    Scaffold(
        topBar = { TopBar(title = "Members") }
    ) { paddingValues ->

        if (state.isLoading)
            ShimmerList()
        else
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
            ) {
                items(state.members) { members ->
                    MemberItem(
                        item = members,
                        modifier = Modifier.padding(paddingValues),
                        onItemClick = { name -> onItemClick(name) }
                    )
                }
            }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemberItem(
    item: Members,
    modifier: Modifier,
    onItemClick: (name: String) -> Unit
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .aspectRatio(0.9f)
            .padding(8.dp),
        onClick = { onItemClick(item.name) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
        ) {
            MemberImage(
                imageUrl = item.imageUrl,
                modifier = modifier
            )
            MemberName(
                name = item.name,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun MemberName(name: String, fontSize: TextUnit) {
    Text(
        text = name,
        style = MaterialTheme.typography.body2,
        fontSize = fontSize
    )
}

@Composable
fun MemberImage(imageUrl: String, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
//            .placeholder()
//            .error()
//            .fallback()
//            .networkCachePolicy(CachePolicy.ENABLED)
//            .memoryCachePolicy(CachePolicy.ENABLED)
//            .memoryCacheKey(imageUrl)
//            .diskCachePolicy(CachePolicy.ENABLED)
//            .diskCacheKey(imageUrl)
//            .size(Size.ORIGINAL)
            .build(),
        contentDescription = "member_image",
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10))

    )


}





