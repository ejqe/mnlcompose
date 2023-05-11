package com.ejqe.mnlapp.members.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejqe.mnlapp.members.domain.Members
import com.ejqe.mnlapp.members.presentation.list_members.MemberImage
import com.ejqe.mnlapp.members.presentation.list_members.MemberName

@Composable
fun DetailsScreen(
    item: Members,
    onOshiClick: (name: String, oldValue: Boolean) -> Unit) {
    val icon = if (item.isOshi) Icons.Filled.Favorite
    else Icons.Filled.FavoriteBorder


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        MemberImage(
            item.imageUrl,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
            )
        Row (
            verticalAlignment = Alignment.CenterVertically,

                ){
            MemberName(
                name = item.name,
                fontSize = 24.sp

            )
            OshiIcon(icon, modifier = Modifier.weight(0.15f))
            { onOshiClick(item.name, item.isOshi)}
        }


        Text("More info coming soon!")
    }

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

