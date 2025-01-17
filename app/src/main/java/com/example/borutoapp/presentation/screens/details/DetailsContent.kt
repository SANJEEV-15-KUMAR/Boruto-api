package com.example.borutoapp.presentation.screens.details


import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.common.InfoBox
import com.example.borutoapp.presentation.common.orderedList
import com.example.borutoapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.example.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.titleColor
import com.example.borutoapp.util.Constants.ABOUT_TEXT_MAX_LINE
import com.example.borutoapp.util.Constants.BASE_URL
import com.example.borutoapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?,
    colors:Map<String,String>
){

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedHero){
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrant)),
    )


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded )
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if(currentSheetFraction==1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL)

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT ,
        sheetContent = {
                      selectedHero?.let {
                          BottomSheetContent(
                              selectedHero = it,
                              infoBoxIconColors = Color(parseColor(vibrant)),
                              sheetBackgroundColor = Color(parseColor(darkVibrant)),
                              contentColor = Color(parseColor(onDarkVibrant)),
                          ) }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor =Color(parseColor(darkVibrant)),
                    onClosedClicked = {
                        navController.popBackStack()
                    }
                )
            }

        }
    )
}

@Composable
fun BottomSheetContent(
    selectedHero:Hero,
    infoBoxIconColors: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor:Color = MaterialTheme.colors.surface,
    contentColor:Color = MaterialTheme.colors.titleColor
){
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
              modifier = Modifier
                  .size(INFO_ICON_SIZE)
                  .weight(2f),
              painter = painterResource(id = R.drawable.logo),
              contentDescription =stringResource(id = R.string.app_logo),
              tint = contentColor
          )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
                )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id =R.drawable.bolt),
                iconColor =infoBoxIconColors ,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor =contentColor
            )

            InfoBox(
                icon = painterResource(id =R.drawable.calendar),
                iconColor =infoBoxIconColors ,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor =contentColor
            )

            InfoBox(
                icon = painterResource(id =R.drawable.cake),
                iconColor =infoBoxIconColors ,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor =contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines =ABOUT_TEXT_MAX_LINE
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            orderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )

            orderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )

            orderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage:String,
    imageFraction:Float = 1f,
    backgroundColor:Color = MaterialTheme.colors.surface,
    onClosedClicked: ()-> Unit
){

    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl){
        error(R.drawable.placeholder)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = (imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                        .coerceAtMost(1.0f)
                )
                .align(Alignment.TopCenter),
            painter =painter ,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all= SMALL_PADDING),
                onClick = {onClosedClicked}
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
   }
@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        Log.d("Fraction",fraction.toString())
        Log.d("Fraction Target",targetValue.toString())
        Log.d("Fraction current",currentValue.toString())

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview
fun BottomSheetContentPreview(){
    BottomSheetContent(selectedHero = Hero(
        id = 1,
        name = "Naruto",
        image = "",
        about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        rating = 4.5,
        power = 0,
        month = "Oct",
        day = "1st",
        family = listOf("Minato", "Kushina", "Boruto", "Himawari"),
        abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
        natureTypes = listOf("Earth", "Wind")
    )
    )

}


