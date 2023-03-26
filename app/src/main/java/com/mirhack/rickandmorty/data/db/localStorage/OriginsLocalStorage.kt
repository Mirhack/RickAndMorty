package com.mirhack.rickandmorty.data.db.localStorage

import javax.inject.Inject
import androidx.room.withTransaction
import com.mirhack.rickandmorty.data.db.Database
import com.mirhack.rickandmorty.data.network.mapper.toEntity
import com.mirhack.rickandmorty.data.network.model.LocationLinkDTO


class OriginsLocalStorage @Inject constructor(
    private val database: Database,
) {

    suspend fun save(links: List<LocationLinkDTO>) {
        database.withTransaction {
            database.originsDao().insertAll(links.map { it.toEntity() })
        }
    }
}