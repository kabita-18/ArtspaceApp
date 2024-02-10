package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtspaceAppTheme
import com.example.artspaceapp.ui.theme.Artwork
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceAppTheme {
                // A surface container using the 'background' color from the theme
                ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var currentArtwork by remember {
        mutableStateOf(1)
    }
    val artworks = listOf(
        Artwork(
            titleResourceId = R.string.purple_flower,
            artistResourceId = R.string.Polina,
            yearResourceId = R.string.flower_year,
            imageResourceId = R.drawable.image1
        ),
        Artwork(
            titleResourceId = R.string.deer,
            artistResourceId = R.string.OMK,
            yearResourceId = R.string.flower_year,
            imageResourceId = R.drawable.image2
        ),
        Artwork(
            titleResourceId = R.string.man_head,
            artistResourceId = R.string.simonlee,
            yearResourceId = R.string.flower_year,
            imageResourceId = R.drawable.image3
        )
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        val artwork = artworks.getOrNull(currentArtwork - 1)
        artwork?.let { art ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(1.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .padding(40.dp)
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = art.imageResourceId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(350.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
            ArtworkDescriptor(
                titleResourceId = art.titleResourceId,
                artistResourceId = art.artistResourceId,
                year = art.yearResourceId
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DisplayController(ResourceButtonId = R.string.previous) {
                    if (currentArtwork > 1) {
                        currentArtwork--
                    }
                }
                DisplayController(ResourceButtonId = R.string.next) {
                    if (currentArtwork < artworks.size) {
                        currentArtwork++
                    }
                }
            }
        }
    }
}

@Composable
fun ArtworkDescriptor(
    titleResourceId: Int,
    artistResourceId: Int,
    year: Int,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color(227, 226, 243))
                .height(120.dp)
                .width(500.dp)
                .padding(start = 15.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = titleResourceId),
                modifier = Modifier,
                fontSize = 25.sp,
                fontWeight = FontWeight.Light,
            )
            Text(
                text = "${stringResource(id = artistResourceId)} (${stringResource(id = year)})",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DisplayController(
    ResourceButtonId: Int,
    onClickbutton: () -> Unit
) {
    Column(modifier = Modifier) {
        Button(
            onClick = { onClickbutton() },
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = stringResource(id = ResourceButtonId),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
                    .width(60.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtspaceAppTheme {
        ArtSpaceLayout()
    }
}
