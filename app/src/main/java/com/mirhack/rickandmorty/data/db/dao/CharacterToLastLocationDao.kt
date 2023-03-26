package com.mirhack.rickandmorty.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mirhack.rickandmorty.data.db.TABLE_CHARACTER_TO_LAST_LOCATION
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToLastLocationCrossRef

@Dao
abstract class CharacterToLastLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(entities: List<CharacterToLastLocationCrossRef>)

    @RawQuery
    suspend fun clearTable() {
        SimpleSQLiteQuery("DELETE FROM $TABLE_CHARACTER_TO_LAST_LOCATION")
    }
}