package com.mirhack.rickandmorty.data.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.mirhack.rickandmorty.data.db.TABLE_CHARACTERS
import com.mirhack.rickandmorty.data.db.model.CharacterEntity
import com.mirhack.rickandmorty.data.db.model.CharacterFull

@Dao
abstract class CharactersDao {

    @Transaction
    @Query("SELECT * FROM $TABLE_CHARACTERS")
    abstract fun getAll(): DataSource.Factory<Int, CharacterFull>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    abstract fun insertAll(characters: List<CharacterEntity>)

    @RawQuery
    fun clearTable() {
        SimpleSQLiteQuery("DELETE FROM $TABLE_CHARACTERS")
    }
}