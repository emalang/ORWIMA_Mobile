package hr.ferit.langema.booktopia.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hr.ferit.langema.booktopia.R
import hr.ferit.langema.booktopia.data.BookshelfBook
import hr.ferit.langema.booktopia.data.BookshelfViewModel
import hr.ferit.langema.booktopia.ui.theme.Gray
import hr.ferit.langema.booktopia.ui.theme.Pink80
import hr.ferit.langema.booktopia.ui.theme.White

@Composable
fun BookshelfScreen(viewModel: BookshelfViewModel, navigation: NavController) {
    
    LaunchedEffect(Unit) {
        viewModel.fetchDatabaseData()
    }

    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var genre by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TopImageAndBar(navigation = navigation)

        Text(
            "Make Your Bookshelf",
            style = MaterialTheme.typography.titleLarge,
        )

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Pink80,
                cursorColor = Pink80
            )
        )

        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Pink80,
                cursorColor = Pink80
            )
        )

        TextField(
            value = genre,
            onValueChange = { genre = it },
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Pink80,
                cursorColor = Pink80
            )
        )

        Button(
            onClick = {
                if (title.isNotEmpty() && author.isNotEmpty() && genre.isNotEmpty()) {
                    viewModel.addBook(BookshelfBook(title = title, author = author, genre = genre))
                    title = ""
                    author = ""
                    genre = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Pink80)
        ) {
            Text("Add Book")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.books) { book ->
                BookshelfBookRow(
                    book = book,
                    onDelete = { viewModel.removeBook(book) }
                )
            }
        }
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = Gray,
    elevation: ButtonElevation? =
        ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
    onClick: () -> Unit = {}
) {
    Button(
        contentPadding = PaddingValues(),
        elevation = elevation,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = White,
            contentColor = color),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
        )
    }
}

@Composable
fun TopImageAndBar(
    navigation: NavController,
) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 4.dp, end = 16.dp, top = 16.dp)
            ){
                CircularButton(
                    iconResource = R.drawable.ic_arrow_back,
                    onClick={navigation.navigateUp()}
                )

            }
        }
    }
}

@Composable
fun BookshelfBookRow(book: BookshelfBook, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Title: ${book.title}", style = MaterialTheme.typography.titleMedium)
                Text("Author: ${book.author}", style = MaterialTheme.typography.bodyMedium)
                Text("Genre: ${book.genre}", style = MaterialTheme.typography.bodyMedium)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {}

            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(
                    "Delete",
                )
            }
        }
    }
}
