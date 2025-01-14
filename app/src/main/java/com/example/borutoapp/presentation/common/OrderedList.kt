package com.example.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.titleColor

@Composable
fun orderedList(
    title:String,
    items:List<String>,
    textColor:Color
){
    Column {
        Text(
          modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed{ index,item  ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index+1}. $item ",
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun orderedListPreview(){
    orderedList(
        title = "Family",
        items = listOf("me ","you" , "mine"),
        textColor =MaterialTheme.colors.titleColor
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun orderedListDarkPreview(){
    orderedList(
        title = "Family",
        items = listOf("me ","you" , "mine"),
        textColor =MaterialTheme.colors.titleColor
    )
}