package com.example.news

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news.ui.theme.NewsTheme
import java.util.*

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
private  data class rubriqueInfo(@StringRes val title: Int)

private val infosImtes = listOf<Info>(
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
    Info(R.drawable.listenmusicdontlike, R.string.info_title, R.string.information),
)
private val rubriqueInfoItems = listOf<rubriqueInfo>(
    rubriqueInfo(R.string.r_local),
    rubriqueInfo(R.string.r_International),
    rubriqueInfo(R.string.r_sport),
    rubriqueInfo(R.string.r_e_sport),
)

@Composable
fun MainApplication(){
    Scaffold(
        topBar ={
            TopToolBar(modifier = Modifier.padding(), title = R.string.app_name)
        }
    ) {padding ->
        InfoScreen()
        Spacer(modifier = Modifier.padding(20.dp))
    }
}


@Composable
fun TopToolBar(modifier: Modifier, @StringRes title : Int){
    TopAppBar(
        title = { Text(stringResource(id = title)) },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Default.Home, contentDescription = null)
            }
        },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            /*IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Default.Share, contentDescription = "Share App")
            }*/
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh news data")
            }
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
    val currentDateTime = Date()
    val context = LocalContext.current
   Surface(
       elevation = 10.dp,
       modifier = modifier,
       color = Color.Gray,
       shape = MaterialTheme.shapes.medium
   ) {
      Card(
          elevation = 10.dp
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
                  modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
              )
              Button(
                  modifier = Modifier.padding(start = 10.dp),
                  onClick = { ShowMessage(context,"Waiting for the internet...")}) {
                  Text(text = "Read more")
              }
              Spacer(Modifier.padding(20.dp))
              Text(
                  text = "$currentDateTime",
                  color = MaterialTheme.colors.primary,
                  style = MaterialTheme.typography.overline,
                  modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
              )
          }
      }
   }
}

@Composable
fun Rubrique(
    modifier: Modifier = Modifier,
    @StringRes rubriqueTitle: Int
){
    val context = LocalContext.current
    val stringTitle = stringResource(id = rubriqueTitle)
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.clickable
        {
            ShowMessage(context, "$stringTitle news clicked")
        }
    ) {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .height(40.dp)
                .width(100.dp),
            backgroundColor = MaterialTheme.colors.primary,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement =  Arrangement.Center
            ) {
                Text(
                    stringResource(id = rubriqueTitle),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
fun SectionRubriqueInfo(){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp)
    ){
        items(rubriqueInfoItems){item ->
            Rubrique(rubriqueTitle = item.title)
        }
    }
}

@Composable
fun Section(
    @StringRes title: Int,
    content : @Composable ()-> Unit
){
    Column {
        Text(
            text = stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .paddingFromBaseline(top = 20.dp, bottom = 10.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun Inforamtion(){
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

@Composable
fun InfoScreen(){
    Column {
        Spacer(modifier = Modifier.padding(10.dp))
        Section(title = R.string.info_rubrique_title) {
            SectionRubriqueInfo()
        }
        Spacer(modifier = Modifier.padding(10.dp))
       Section(title = R.string.inforamtion_rubrique_title){
           Inforamtion()
       }
    }
}

/*-----------------functions--------------------*/
private fun ShowMessage(context: Context, message: String){
Toast.makeText(context,message, Toast.LENGTH_LONG).show()
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

@Preview(showBackground = true)
@Composable
fun RubriquePreview(){
    NewsTheme {
        Rubrique(rubriqueTitle = R.string.r_local)
    }
}

@Preview(showBackground = true)
@Composable
fun SectionRubriqueInfoPreview(){
    NewsTheme {
        SectionRubriqueInfo()
    }
}

@Preview(showBackground = true)
@Composable
fun InformationPreview(){
    NewsTheme {
        Inforamtion()
    }
}

@Preview(showBackground = true)
@Composable
fun SectionPreview(){
    NewsTheme {
        Section(title = R.string.info_rubrique_title ) {
            SectionRubriqueInfo()
        }
    }
}