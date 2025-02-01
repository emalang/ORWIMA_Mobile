package hr.ferit.langema.booktopia.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class BookshelfViewModel : ViewModel() {
    private val db = Firebase.firestore
    val books = mutableStateListOf<BookshelfBook>()
    init {
        fetchDatabaseData()
    }

    fun addBook(book: BookshelfBook) {
        val bookId = db.collection("bookshelf").document().id
        val bookWithId = book.copy(id = bookId)

        db.collection("bookshelf")
            .document(bookId)
            .set(bookWithId)
            .addOnSuccessListener { books.add(bookWithId) }
            .addOnFailureListener {   }
    }

     fun fetchDatabaseData() {
        db.collection("bookshelf")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val book = data.toObject(BookshelfBook::class.java)
                    if (book != null) {
                        book.id = data.id
                        books.add(book)
                    }
                }
            }
    }

    fun removeBook(book: BookshelfBook) {
        db.collection("bookshelf")
            .document(book.id)
            .delete()
            .addOnSuccessListener { books.remove(book) }
            .addOnFailureListener {  }
    }


}
