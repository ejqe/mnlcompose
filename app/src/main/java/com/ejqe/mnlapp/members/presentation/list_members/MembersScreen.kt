package com.ejqe.mnlapp.members.presentation.list_members

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.ejqe.mnlapp.ui.widgets.SearchBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MembersScreen(
    state: MembersScreenState,
    paddingValues: PaddingValues,
    onItemClick: (name: String) -> Unit,
) {

    Column(modifier = Modifier.padding(paddingValues)) {
        SearchBar()

        if (state.isLoading)
            ShimmerList(paddingValues = paddingValues)
        else
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
            ) {
                items(state.members) { members ->
                    MemberItem(
                        item = members,
                        modifier = Modifier.padding(4.dp),
                        onItemClick = { name -> onItemClick(name) }
                    )
                }
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberItem(
    item: Members,
    modifier: Modifier,
    onItemClick: (name: String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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





