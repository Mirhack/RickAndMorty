package com.mirhack.rickandmorty.presentation.navigation

sealed class Routes(val route: String) {
    object MainScreen : Routes("mainScreen")
    object CharactersScreen : Routes("charactersScreen")
}