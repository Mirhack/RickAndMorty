package com.mirhack.rickandmorty.domain

import com.mirhack.rickandmorty.domain.model.Character

interface Repository {

    suspend fun getCharacter(id: Int): Character

}