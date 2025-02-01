package hr.ferit.langema.booktopia.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CategoryViewModel: ViewModel() {
    private val db = Firebase.firestore
    val categoriesData = mutableStateListOf<Category>()
    init {
        fetchDatabaseData()
    }
    private fun fetchDatabaseData() {
        db.collection("categories")
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val category = data.toObject(Category::class.java)
                    if (category != null) {
                        category.id = data.id
                        categoriesData.add(category)
                    }
                }
            }
    }
}


