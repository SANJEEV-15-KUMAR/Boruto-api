package com.example.borutoapp.presentation.screens.Home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R

@Composable
fun HomeTopBar(onSearchedClicked:()->Unit){

     TopAppBar(title = { 
        Text(
            text = "Explore",
            color  = colorResource(id = R.color.home_top_app_bar_content_color)
            )
     },
     backgroundColor = colorResource(id = R.color.home_top_app_bar_background_color),
         actions = {
            IconButton(onClick = onSearchedClicked) {
                Icon(
                    modifier  = Modifier.background(colorResource(id = R.color.home_top_app_bar_content_color)),
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    
                 )
            }
         }
     )
 }

@Composable
@Preview
fun HomeTopBarPreview(){
    HomeTopBar {}
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HomeTopBarPreviewDark(){
    HomeTopBar {}
}