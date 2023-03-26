package com.mirhack.rickandmorty.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mirhack.rickandmorty.data.db.TABLE_CHARACTER_TO_ORIGIN
import com.mirhack.rickandmorty.data.db.crossrefs.CharacterToOriginCrossRef

@Dao
abstract class CharacterToOriginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(entities: List<CharacterToOriginCrossRef>)

    @RawQuery
    suspend fun clearTable() {
        SimpleSQLiteQuery("DELETE FROM $TABLE_CHARACTER_TO_ORIGIN")
    }
}