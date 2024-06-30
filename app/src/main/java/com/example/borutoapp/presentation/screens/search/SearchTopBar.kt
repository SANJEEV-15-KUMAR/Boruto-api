package com.example.borutoapp.presentation.screens.search


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.LightGray
import com.example.borutoapp.ui.theme.Purple500
import com.example.borutoapp.ui.theme.TOP_APP_BAR_HEIGHT

@Composable
fun SearchTopBar(
    text:String,
    onTextChanged:(String) -> Unit,
    onSearchClicked:(String) -> Unit,
    onCloseClicked :() -> Unit
){
    SearchWidget(
        text = text,
        onTextChanged = onTextChanged,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}

@Composable
fun SearchWidget(
    text:String,
    onTextChanged:(String) -> Unit,
    onSearchClicked:(String) -> Unit,
    onCloseClicked :() -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .semantics {
                       contentDescription ="SearchWidget"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color =  if (isSystemInDarkTheme()) Color.Black else Purple500
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                           contentDescription = "TextField"
                },
            value = text,
            onValueChange = {onTextChanged(it)},
            placeholder = {
                Text(
                    modifier = Modifier.
                    alpha( alpha = ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = if (isSystemInDarkTheme()) LightGray  else Color.White
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = if (isSystemInDarkTheme()) LightGray else Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.semantics {
                        contentDescription ="CloseButton"
                    },
                    onClick = {
                        if(text.isNotEmpty()){
                            onTextChanged("")
                        }else{
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon) ,
                        tint = if (isSystemInDarkTheme()) LightGray else Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions (
                    onSearch = {
                        onSearchClicked(text)
                    }
                    ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = if (isSystemInDarkTheme()) LightGray else Color.White
            )
        )
    }
}

@Composable
@Preview
fun SearchWidgetPreview(){
    SearchWidget(
        text = "",
        onTextChanged = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}