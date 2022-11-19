package com.mirhack.rickandmorty.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mirhack.rickandmorty.presentation.navigation.Routes
import com.mirhack.rickandmorty.presentation.screens.characterInfo.CharacterInfoScreen
import com.mirhack.rickandmorty.presentation.screens.charactersList.CharactersListScreen
import com.mirhack.rickandmorty.presentation.screens.episodeInfo.EpisodeInfoScreen
import com.mirhack.rickandmorty.presentation.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.CharactersListScreen.route
                    ) {
                        composable(Routes.CharactersListScreen.route) {
                            CharactersListScreen(navController)
                        }
                        composable(
                            route = Routes.CharacterInfoScreen.route + "/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            CharacterInfoScreen(
                                id = backStackEntry.arguments?.getInt("id"),
                                navController = navController
                            )
                        }
                        composable(
                            route = Routes.EpisodeInfoScreen.route + "/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry -> EpisodeInfoScreen(backStackEntry.arguments?.getInt("id")) }
                    }
                }
            }
        }
    }
}