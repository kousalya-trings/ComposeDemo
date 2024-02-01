package com.kousalya.composedemo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kousalya.composedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun Context.openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    }

    @OptIn(ExperimentalFoundationApi::class)
    private fun initView() {
        val items = createItems()
        binding.composeView.setContent {
            val pager = rememberPagerState { items.size }
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(title = { Text("Compose Demo") })
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        openUrlInBrowser(items[pager.currentPage].siteUrl)
                    }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "Navigate")
                    }
                }
            ) {
                HorizontalPager(
                    state = pager,
                    modifier = Modifier.padding(it),
                ) {
                    Column {
                        Text(
                            text = items[it].name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                        AsyncImage(
                            model = items[it].imageUrl,
                            contentDescription = "Android version images",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

data class HorizontalPagerContent(
    val name: String,
    val imageUrl: String,
    val siteUrl: String,
)

fun createItems() = mutableListOf(
    HorizontalPagerContent(
        name = "Marshmallow",
        imageUrl = "https://eitnetworks.net/wp-content/uploads/2016/05/android-6.0-marshmallow-3.jpg",
        siteUrl = "https://www.android.com/intl/en_in/versions/marshmallow-6-0/"
    ),
    HorizontalPagerContent(
        name = "Oreo",
        imageUrl = "https://img2.helpnetsecurity.com/posts/android-oreo.jpg",
        siteUrl = "https://www.android.com/intl/en_in/versions/oreo-8-0/"
    ),
    HorizontalPagerContent(
        name = "Jelly Bean",
        imageUrl = "https://pakorbit.com/wp-content/uploads/2013/07/android-jelly-bean-face-unlock-648x4321.jpeg",
        siteUrl = "https://www.android.com/intl/en_in/versions/jelly-bean-4-3/"
    ),
    HorizontalPagerContent(
        name = "Lollipop",
        imageUrl = "https://e7.pngegg.com/pngimages/758/853/png-clipart-android-lollipop-smartphone-rooting-android-food-lollipop.png",
        siteUrl = "https://www.android.com/intl/en_in/versions/lollipop-5-0/"
    ),
    HorizontalPagerContent(
        name = "Kitkat",
        imageUrl = "https://4.bp.blogspot.com/-NoYzgJkUzkI/UnH89i6ZSgI/AAAAAAAACsU/nIOF3laWFLk/s1600/kk-hero.png",
        siteUrl = "https://www.android.com/intl/en_in/versions/kit-kat-4-4/"
    )
)