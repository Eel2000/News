package com.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.news.ui.theme.NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
               MainApplication()
            }
        }
    }
}

private  data class Info(
    @DrawableRes val image: Int,
    @StringRes val title: Int ,
    @StringRes val text: Int
    )
private val infosImtes = listOf<Info>(
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
)

@Composable
fun MainApplication(){
    Scaffold(
        topBar ={
            TopToolBar(modifier = Modifier.padding(), title = R.string.app_name)
        }
    ) {padding ->
        //TODO : Add horizontal layout to choose info type above the info layout
        InfoScreen()
    }
}


@Composable
fun TopToolBar(modifier: Modifier, @StringRes title : Int){
    TopAppBar(
        title = { Text(stringResource(id = title)) },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            /*IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Default.Share, contentDescription = "Share App")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Localized description")
            }*/
        }
    )
}

@Composable
fun CardInfo(
    modifier: Modifier,
    @DrawableRes image: Int,
    @StringRes title: Int,
    @StringRes information: Int
){
    val currentDateTime = java.util.Date()
   Surface(
       elevation = 10.dp,
       modifier = modifier,
       color = Color.Gray,
       shape = MaterialTheme.shapes.medium
   ) {
       Column(modifier) {
           Image(
               painterResource(id = image),
               contentDescription =null
           )
           Text(
               stringResource(id = title),
               style = MaterialTheme.typography.h6,
               modifier = Modifier
                   .padding(horizontal = 10.dp, vertical = 10.dp)
                   .padding(end = 10.dp)
           )
           Text(
               stringResource(id = information),
               style = MaterialTheme.typography.body2,
               modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 16.dp)
           )
           Spacer(Modifier.padding(1.dp))
           Text(
               text = "$currentDateTime",
               color = Color.White,
               style = MaterialTheme.typography.overline,
               modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
           )
       }
   }
}

@Composable
fun InfoScreen(){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(50.dp),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp)
    ){
        items(infosImtes){item ->
            CardInfo(
                modifier = Modifier.padding(),
                image =item.image ,
                title = item.title ,
                information = item.text
            )
        }
    }
}

/*-----------------Preview---------------------*/
@Preview(showBackground = true)
@Composable
fun ApplicationPreview(){
    NewsTheme {
        MainApplication()
    }
}

@Preview(showBackground = true)
@Composable
fun TopToolBarPreview() {
    NewsTheme {
        TopToolBar(modifier = Modifier.padding(), title = R.string.app_name)
    }
}

@Preview(showBackground = true)
@Composable
fun CardInfoPreview(){
    NewsTheme {
        CardInfo(
            modifier = Modifier.padding(),
            image = R.drawable.listenmusicdontlike,
            title = R.string.info_title,
            information = R.string.information
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview(){
    NewsTheme {
        InfoScreen()
    }
}