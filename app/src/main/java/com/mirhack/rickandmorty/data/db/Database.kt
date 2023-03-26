package com.mirhack.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToLocationLinkCrossRef
import com.mirhack.rickandmorty.data.db.dao.CharactersDao
import com.mirhack.rickandmorty.data.db.model.CharacterEntity
import com.mirhack.rickandmorty.data.db.model.Converters
import com.mirhack.rickandmorty.data.db.model.LocationLinkEntity

@Database(
    version = 1,
    entities = [
        CharacterEntity::class,
        LocationLinkEntity::class,
        CharacterToLocationLinkCrossRef::class,
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
}