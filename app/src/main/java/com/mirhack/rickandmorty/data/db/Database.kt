package com.mirhack.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToLastLocationCrossRef
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToOriginCrossRef
import com.mirhack.rickandmorty.data.db.dao.CharacterToLastLocationDao
import com.mirhack.rickandmorty.data.db.dao.CharacterToOriginDao
import com.mirhack.rickandmorty.data.db.dao.CharactersDao
import com.mirhack.rickandmorty.data.db.dao.LastLocationDao
import com.mirhack.rickandmorty.data.db.dao.OriginDao
import com.mirhack.rickandmorty.data.db.model.CharacterEntity
import com.mirhack.rickandmorty.data.db.model.Converters
import com.mirhack.rickandmorty.data.db.model.LocationLinkEntity

@Database(
    version = 1,
    entities = [
        CharacterEntity::class,
        LocationLinkEntity::class,
        CharacterToLastLocationCrossRef::class,
        CharacterToOriginCrossRef::class,
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun lastLocationsDao(): LastLocationDao
    abstract fun originsDao(): OriginDao
    abstract fun characterToOriginDao(): CharacterToOriginDao
    abstract fun characterToLastLocationDao(): CharacterToLastLocationDao
}