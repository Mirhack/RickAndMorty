package com.mirhack.rickandmorty.presentation.navigation

sealed class Routes(val route: String) {
    object CharactersListScreen : Routes("charactersScreen")
    data class CharacterInfoScreen(val id: String) : Routes("characterInfoScreen/$id")
    data class EpisodeInfoScreen(val id: String) : Routes("episodeInfoScreen/$id")
    data class LocationInfoScreen(val id: String) : Routes("locationInfoScreen/$id")
}