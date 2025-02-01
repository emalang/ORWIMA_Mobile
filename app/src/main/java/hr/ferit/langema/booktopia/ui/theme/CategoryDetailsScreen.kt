package hr.ferit.langema.booktopia.ui.theme

import androidx.compose.foundation.lazy.items
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import hr.ferit.langema.booktopia.R
import hr.ferit.langema.booktopia.data.Book
import hr.ferit.langema.booktopia.data.Category
import hr.ferit.langema.booktopia.data.CategoryViewModel

@Composable
fun CategoryDetailsScreen(
    viewModel: CategoryViewModel,
    navigation: NavController,
    categoryId: Int
){

    val category = viewModel.categoriesData[categoryId]
    Column(modifier = Modifier.fillMaxSize()) {

        TopImageAndBar(navigation = navigation)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            BookListDisplay(category)
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
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
fun BookListDisplay(category: Category) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

    ) {
        items(category.books) { book ->
                BookCard(book =book)
            }
        }
    }



@Composable
fun BookCard(book: Book) {
    Card(
        border = BorderStroke(1.dp, Pink80),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(end=8.dp)) {
                Text(
                    text = book.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = book.author,
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = book.description,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Card(
                modifier = Modifier
                    .size(width = 150.dp, height = 220.dp),
                shape=RoundedCornerShape(15.dp)
            ) {
                Box(
                    modifier=Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = book.image),
                        contentDescription = "Book image",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}

