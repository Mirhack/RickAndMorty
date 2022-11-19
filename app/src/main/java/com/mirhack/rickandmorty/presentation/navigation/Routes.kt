package com.mirhack.rickandmorty.presentation.navigation

sealed class Routes(val route: String) {
    object CharactersListScreen : Routes("charactersScreen")
    object CharacterInfoScreen : Routes("characterInfoScreen")
    object EpisodeInfoScreen : Routes("episodeInfoScreen")
}