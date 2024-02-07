package com.kousalya.composedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.kousalya.composedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ExampleViewModel::class.java]
        setContentView(binding.root)
        binding.composeView.setContent {
            ScaffoldLibrary()
        }
    }

    @Composable
    fun ScaffoldLibrary() {
        val scrollState = rememberLazyListState()
        val scrollUpState = viewModel.scrollUp.observeAsState()

        viewModel.updateScrollPosition(scrollState.firstVisibleItemIndex)

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 56.dp),
                state = scrollState
            ) {
                items(books) { book ->
                    Book(model = book)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            ScrollableAppBar(
                title = "ScrollableAppBarExample",
                modifier = Modifier.align(Alignment.CenterStart),
                scrollUpState = scrollUpState,
            )
        }
    }

    @Composable
    fun ScrollableAppBar(
        title: String,
        modifier: Modifier = Modifier,
        navigationIcon: @Composable() (() -> Unit)? = null,
        background: Color = MaterialTheme.colors.primary,
        scrollUpState: State<Boolean?>,
    ) {
        val position by animateFloatAsState(
            if (scrollUpState.value == true) -150f else 0f,
            label = "Scrollable AppBar"
        )

        Surface(modifier = Modifier.graphicsLayer { translationY = (position) }, elevation = 8.dp) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = background),
            )
            Row(modifier = modifier.padding(start = 12.dp)) {
                if (navigationIcon != null) {
                    navigationIcon()
                }
                Text(text = title)
            }
        }
    }

    data class BookModel(val title: String, val author: String, val pageCount: Int)

    private val books = listOf(
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