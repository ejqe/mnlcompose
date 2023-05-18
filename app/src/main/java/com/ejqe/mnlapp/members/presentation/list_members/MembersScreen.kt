package com.ejqe.mnlapp.members.presentation.list_members

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ejqe.mnlapp.members.domain.model.Members
import com.ejqe.mnlapp.ui.widgets.TextInput


@Composable
fun MembersScreen(
    state: MembersScreenState,
    onItemClick: (name: String) -> Unit,
) {
    val text = remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            val userName = "Ric Yujin"
            Column {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier
                            .weight(5f)
                    ) {
                        androidx.compose.material.Text(
                            "Hello",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Gray,
                            )
                        )
                        androidx.compose.material.Text(
                            userName,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }

                    IconButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu button"
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(10.dp),
                            clip = true
                        )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = Modifier
                                .padding(start = 8.dp)
                        )
                        TextInput(
                            modifier = Modifier
                                .weight(1f),
                            inputValue = text,
                            placeholder = "Search Members",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Face,
                                contentDescription = "Mic Button",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                            )
                        }
                    }
                }
            }
        }

    ) { paddingValues ->

        if (state.isLoading)
            ShimmerList()
        else
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                modifier = Modifier.padding(paddingValues)
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





