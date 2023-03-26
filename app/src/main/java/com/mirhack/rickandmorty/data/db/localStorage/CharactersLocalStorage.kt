package com.mirhack.rickandmorty.data.db.localStorage

import javax.inject.Inject
import androidx.room.withTransaction
import com.mirhack.rickandmorty.data.db.Database
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToLastLocationCrossRef
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToOriginCrossRef
import com.mirhack.rickandmorty.data.network.mapper.toEntity
import com.mirhack.rickandmorty.data.network.model.CharacterDTO


class CharactersLocalStorage @Inject constructor(
    private val database: Database,
    private val lastLocationsLocalStorage: LastLocationsLocalStorage,
    private val originsLocalStorage: OriginsLocalStorage,
) {
    fun clearTable() {
        database.charactersDao().clearTable()
    }

    suspend fun save(characters: List<CharacterDTO>) {
        database.withTransaction {
            database.charactersDao().insertAll(characters.map { it.toEntity() })
        }

        originsLocalStorage.save(characters.map { it.origin })
        lastLocationsLocalStorage.save(characters.map { it.location })
        database.characterToOriginDao().insertOrUpdate(
            characters.map { CharacterToOriginCrossRef(it.id, it.origin.name) })
        database.characterToLastLocationDao().insertOrUpdate(
            characters.map { CharacterToLastLocationCrossRef(it.id, it.location.name) })
    }
}