package hr.ferit.langema.booktopia.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import hr.ferit.langema.booktopia.Routes
import hr.ferit.langema.booktopia.ui.theme.Pink
import hr.ferit.langema.booktopia.ui.theme.Pink80
import hr.ferit.langema.booktopia.data.CategoryViewModel


@Preview(showBackground = true)
@Composable
fun MainScreen(modifier: Modifier = Modifier, navigation: NavController,viewModel: CategoryViewModel) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ScreenTitle(
            subtitle1= "Recommendation page",
            title="An imagined perfect place for book lovers.",
            subtitle2="You’re unique and so is your reading taste.Whether you’re a hopeless romantic or simply appreciate literature, find the best book recommendations with Booktopia."
        )

        CategoryList(viewModel,navigation)


        Button(onClick = { navigation.navigate("bookshelf") },
                colors = ButtonDefaults.buttonColors(containerColor = Pink80)
        ) {
            Text("Make Your Own Bookshelf")
        }


    }
}

@Composable
fun ScreenTitle(
    title: String,
    subtitle1: String,
    subtitle2: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = subtitle1,
            style = TextStyle(
                color = Pink,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = subtitle2,
            style = TextStyle(
                color = Pink,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        )
    }
}


@Composable
fun CategoryCard(modifier: Modifier = Modifier, imageResource:String,title: String,onClick: () -> Unit) {

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier
            .padding(bottom = 16.dp)
            .height(326.dp)
            .width(215.dp)
            .clickable{onClick() }
    ) {

        Box(
            contentAlignment=Alignment.Center,
            modifier=Modifier.fillMaxSize()
        ){

            Image(
                painter = rememberAsyncImagePainter(model = imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            color = Pink80,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .wrapContentSize()
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = title,
                        letterSpacing = 0.32.sp,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .padding(horizontal=8.dp)

                    )
                }
            }
        }
    }
}

@Composable
fun CategoryList(viewModel: CategoryViewModel, navigation: NavController){
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Categories",
                style = TextStyle(color = Color.DarkGray, fontSize = 15.sp)
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(viewModel.categoriesData.size) {
                CategoryCard(
                    imageResource = viewModel.categoriesData[it].image,
                    title = viewModel.categoriesData[it].title
                ) {
                    navigation.navigate(Routes.getCategoryDetailsPath(it))
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
