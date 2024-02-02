package com.kousalya.composedemo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
        binding.composeView.setContent {
            Material3ScaffoldLibrary()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun LibraryTopBar(
        scrollBehavior: TopAppBarScrollBehavior,
    ) = TopAppBar(
        title = {
            Text(
                text = "Library",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Black,
            scrolledContainerColor = Color.Black,
        ),
        scrollBehavior = scrollBehavior,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Material3ScaffoldLibrary(books: List<BookModel> = Books) {
        val listState = rememberLazyListState()
        val scrollBehavior =
            TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { LibraryTopBar(scrollBehavior) }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding),
                state = listState
            ) {
                items(items = books) { book ->
                    Book(model = book)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

    data class BookModel(val title: String, val author: String, val pageCount: Int)

    private val Books = listOf(
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545),
        BookModel(
            title = "Tomorrow and Tomorrow and Tomorrow",
            author = "Gabrielle Zevin",
            pageCount = 416
        ),
        BookModel(title = "Babel", author = "R.F. Kuang", pageCount = 545)
    )

    @Composable
    fun Book(modifier: Modifier = Modifier, model: BookModel) =
        Card(modifier = modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(model.title)
                Text(model.author)
                Text("${model.pageCount} pages")
            }
        }
}