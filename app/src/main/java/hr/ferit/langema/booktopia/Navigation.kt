package hr.ferit.langema.booktopia

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.langema.booktopia.data.BookshelfViewModel
import hr.ferit.langema.booktopia.data.CategoryViewModel
import hr.ferit.langema.booktopia.ui.BookshelfScreen
import hr.ferit.langema.booktopia.ui.MainScreen
import hr.ferit.langema.booktopia.ui.theme.CategoryDetailsScreen
import hr.ferit.langema.booktopia.ui.theme.Pink80

object Routes {
    const val SCREEN_ALL_CATEGORIES = "categoryList"
    const val SCREEN_CATEGORY_DETAILS = "categoryDetails/{categoryId}"
    fun getCategoryDetailsPath(categoryId: Int) : String {
        if ( categoryId != -1) {
            return "categoryDetails/$categoryId"
        }
        return "categoryDetails/0"
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationController(viewModel: CategoryViewModel) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booktopia") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Pink80,
                    titleContentColor = Color.White,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Pink80,
                tonalElevation = 4.dp
            ) {
                Text(
                    text = "© 2025 MyBooktopia",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SCREEN_ALL_CATEGORIES,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.SCREEN_ALL_CATEGORIES) {
                MainScreen(viewModel=viewModel,navigation = navController)
            }
            composable(
                Routes.SCREEN_CATEGORY_DETAILS,
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val catId = backStackEntry.arguments?.getInt("categoryId") ?: 0
                CategoryDetailsScreen(
                    viewModel=viewModel,
                    navigation = navController,
                    categoryId = catId
                )
            }

            composable("bookshelf") {
                val bookshelfViewModel = BookshelfViewModel()
                BookshelfScreen(viewModel = bookshelfViewModel, navigation = navController)
            }

        }
    }
}