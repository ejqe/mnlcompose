package com.ejqe.mnlapp.members.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejqe.mnlapp.members.domain.model.Members
import com.ejqe.mnlapp.members.presentation.list_members.MemberImage
import com.ejqe.mnlapp.members.presentation.list_members.MemberName
import com.ejqe.mnlapp.ui.TopBar

@Composable
fun DetailsScreen(
    item: Members,
    onButtonClick: () -> Unit,
    onOshiClick: (name: String, oldValue: Boolean) -> Unit
) {
    Scaffold(
        topBar = { TopBar(title = item.name) }
    ) { paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MemberImage(
                item.imageUrl,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                MemberName(
                    name = item.name,
                    fontSize = 24.sp
                )
                FavIcon(
                    isChecked = item.isOshi,
                    onClick = { onOshiClick(item.name, item.isOshi) },
                    modifier = Modifier.width(25.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("More info coming soon!")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onButtonClick) {
                Text(text = "Go to OshiScreen")
            }


        }

    }
}

@Composable
fun FavIcon(isChecked: Boolean, onClick: () -> Unit, modifier: Modifier) {
    IconToggleButton(
        checked = isChecked,
        onCheckedChange = { onClick() }
    ) {
        if (isChecked) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = modifier
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}


