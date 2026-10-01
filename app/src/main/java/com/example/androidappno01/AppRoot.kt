package com.example.androidappno01

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidappno01.data.AppDatabase
import com.example.androidappno01.data.NoteRepository
import com.example.androidappno01.navigation.Route
import com.example.androidappno01.ui.detail.DetailScreen
import com.example.androidappno01.ui.detail.DetailViewModel
import com.example.androidappno01.ui.home.HomeScreen
import com.example.androidappno01.ui.home.HomeViewModel

@Composable
fun AppRoot() {
    val context = LocalContext.current
    val repo = remember {
        val db = AppDatabase.get(context)
        NoteRepository(db.noteDao())
    }
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Route.HOME) {
        composable(Route.HOME) {
            val vm: HomeViewModel = viewModel(factory = HomeViewModel.Factory(repo))
            HomeScreen(vm, onOpenDetail = { id -> nav.navigate(Route.detail(id)) })
        }
        composable(
            route = Route.DETAIL,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { entry ->
            val id = entry.arguments?.getLong("noteId") ?: 0L
            val vm: DetailViewModel = viewModel(factory = DetailViewModel.Factory(repo, id))
            DetailScreen(vm, onBack = { nav.popBackStack() })
        }
    }
}
