package com.ejqe.mnlapp.members.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ejqe.mnlapp.MemberImage
import com.ejqe.mnlapp.MemberName
import com.ejqe.mnlapp.OshiIcon
import com.ejqe.mnlapp.members.domain.Members

@Composable
fun DetailsScreen(item: Members) {
    val icon = if (item.isOshi) Icons.Filled.Favorite
    else Icons.Filled.FavoriteBorder


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        MemberImage(item.imageUrl)
        MemberName(item.name)
        OshiIcon(icon, modifier = Modifier.weight(0.15f)) {}


        Text("More info coming soon!")
    }


}

