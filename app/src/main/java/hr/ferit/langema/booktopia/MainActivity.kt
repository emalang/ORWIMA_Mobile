

package hr.ferit.langema.booktopia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import hr.ferit.langema.booktopia.data.CategoryViewModel
import hr.ferit.langema.booktopia.ui.theme.BooktopiaTheme
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CategoryViewModel>()
        enableEdgeToEdge()
        setContent {
            BooktopiaTheme {
                NavigationController(viewModel)
            }
        }
    }
}