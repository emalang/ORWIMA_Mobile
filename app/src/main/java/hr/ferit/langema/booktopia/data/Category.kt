package hr.ferit.langema.booktopia.data
import androidx.annotation.DrawableRes

data class Book(
    val image: String="",
    val title:String="",
    val author:String="",
    val description:String=""
)

data class Category(
    var id:String="",
    val image:String="",
    val title:String="",
    val books: List<Book> = listOf()
)

